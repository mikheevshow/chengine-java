package io.chengine.pipeline.action;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.action.exception.StageActionExecutionException;
import io.chengine.pipeline.action.exception.StageActionNotSupportedException;
import io.chengine.processor.response.AbstractMethodReturnedValueHandler;
import io.chengine.processor.response.MethodReturnedValueHandler;
import io.chengine.processor.response.ResponseTypeHandlerFactory;
import io.chengine.processor.response.ResponseTypeHandlerFactoryAware;
import io.chengine.session.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public abstract class AbstractStageActionMethodReturnedValueHandler
        extends AbstractMethodReturnedValueHandler<StageAction>
        implements SessionCacheAware, ResponseTypeHandlerFactoryAware {

    protected static final Logger log = LogManager.getLogger(AbstractStageActionMethodReturnedValueHandler.class);
    protected SessionCache sessionCache;
    protected ResponseTypeHandlerFactory responseTypeHandlerFactory;

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

        // TODO: ВЫПИЛИТЬ SET RESPONSE OBJECT!!!!
        ((DefaultBotResponseContext) response).put(ActionResponse.class, actionResponse);
        ((DefaultBotResponseContext) response).setResponseObject(actionResponse);
        final MethodReturnedValueHandler<?> nextHandler = responseTypeHandlerFactory.get(actionResponse.getClass());
        setNext(nextHandler);
    }

    protected abstract ActionResponse processStage(StageAction<?> stageAction) throws RuntimeException;

    protected void completeStage() {
        log.info("comleted stage");
        if (isTheLastStage()) {
            terminateSession();
        } else {
            Session session = UserSessionContextHolder.getSession();
            session.getPipelineSessionInfo().incrementStep();
            sessionCache.putSessionBySessionKey(session.getSessionKey(), session);
        }
    }

    protected void terminateSession() {
        log.info("terminate session");
        Session session = UserSessionContextHolder.getSession();
        sessionCache.invalidateCacheBySessionKey(session.getSessionKey());
        UserSessionContextHolder.setSession(null);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseTypeHandlerFactoryAware(ResponseTypeHandlerFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Session cache can't be null");
        }

        this.responseTypeHandlerFactory = factory;
    }

    private boolean isTheLastStage() {
        Session session = UserSessionContextHolder.getSession();
        PipelineSessionInfo sessionInfo = session.getPipelineSessionInfo();
        int currentStep = sessionInfo.getCurrentStep();
        return sessionInfo
                .getPipeline()
                .getStageDefinitions()
                .stream()
                .noneMatch(stageDefinition -> stageDefinition.getStep() > currentStep);
    }
}
