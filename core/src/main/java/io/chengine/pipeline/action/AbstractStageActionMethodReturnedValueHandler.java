package io.chengine.pipeline.action;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.action.exception.StageActionExecutionException;
import io.chengine.pipeline.action.exception.StageActionNotSupportedException;
import io.chengine.processor.AbstractMethodReturnedValueHandler;
import io.chengine.session.SessionCache;
import io.chengine.session.SessionCacheAware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public abstract class AbstractStageActionMethodReturnedValueHandler
        extends AbstractMethodReturnedValueHandler<StageAction>
        implements SessionCacheAware {

    protected static final Logger log = LogManager.getLogger(AbstractStageActionMethodReturnedValueHandler.class);
    protected SessionCache sessionCache;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(
            HandlerMethod handlerMethod,
            StageAction returnedObject,
            BotRequestContext request,
            BotResponseContext response) {

        // Validation before processing
        ((AbstractStageAction<?>) returnedObject).onAssembly();

        ActionResponse actionResponse;
        try {
            actionResponse = processStage(returnedObject);
        } catch (StageActionNotSupportedException ex) {
            throw ex;
        } catch (RuntimeException ex) {

            final AbstractStageAction<?> stageAction = (AbstractStageAction<?>) returnedObject;
            final Consumer<Throwable> throwableConsumer = stageAction.errorConsumer();

            if (throwableConsumer == null) {
                throw new StageActionExecutionException("Exception consumer is not defined, terminate session", ex);
            }

            throwableConsumer.accept(ex);

            if (stageAction.errorActionResponseReturn() != null) {
                actionResponse = stageAction.errorActionResponseReturn().get();
            } else if (stageAction.errorActionResponseResume() != null) {
                actionResponse = stageAction.errorActionResponseReturn().get();
                completeStage();
            } else if (stageAction.errorActionResponseTerminate() != null) {
                actionResponse = stageAction.errorActionResponseReturn().get();
                terminateSession();
            }
            terminateSession();
            throw new NullPointerException("No one error handling method use. Terminate pipeline.");
        }

        ((DefaultBotResponseContext) response).put(ActionResponse.class, actionResponse);
    }

    protected abstract ActionResponse processStage(StageAction<?> stageAction) throws RuntimeException;

    protected void completeStage() {
        log.info("comleted stage");
    }

    protected void terminateSession() {
        log.info("terminate session");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSessionCache(SessionCache sessionCache) {
        if (sessionCache == null) {
            throw new IllegalArgumentException("Session cache can't be null");
        }

        this.sessionCache = sessionCache;
    }
}
