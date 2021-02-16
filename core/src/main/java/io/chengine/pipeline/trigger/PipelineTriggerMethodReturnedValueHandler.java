package io.chengine.pipeline.trigger;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.handler.HandlerRegistry;
import io.chengine.handler.HandlerRegistryAware;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.PipelineDefinition;
import io.chengine.pipeline.StageDefinition;
import io.chengine.processor.AbstractActionResponseMethodReturnedValueHandler;
import io.chengine.session.*;

import java.util.function.Function;

public class PipelineTriggerMethodReturnedValueHandler
        extends AbstractActionResponseMethodReturnedValueHandler
        implements HandlerRegistryAware, SessionCacheAware {

    private HandlerRegistry handlerRegistry;
    private SessionCache sessionCache;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends ActionResponse> supports() {
        return PipelineTrigger.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAllowToProcess(
            HandlerMethod handlerMethod,
            BotRequestContext request,
            BotResponseContext response) {

        final Session session = UserSessionContextHolder.getSession();
        if (session.inPipeline()) {
            log.error("Error triggering new pipeline. User with session key: {}, already in pipeline with name: {}",
                    session.getSessionKey(),
                    session.pipelineSessionInfo().getPipeline()
            );
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(
            HandlerMethod handlerMethod,
            ActionResponse returnedObject,
            BotRequestContext request,
            BotResponseContext response) {

        if (handlerRegistry == null) {
            throw new NullPointerException("Handler registry is null");
        }

        final PipelineTrigger trigger = (PipelineTrigger) returnedObject;
        final PipelineDefinition pipelineDefinition = handlerRegistry.getPipelineDefinitionByName(trigger.getPipelineName());
        final SessionKey sessionKey = request.getSessionKey();
        final Session newSession = createNewPipelineSession(pipelineDefinition, sessionKey);
        // TODO: Потенциальная бага, можно перезатереть сессию с ранее сохраненными данными
        UserSessionContextHolder.setSession(newSession);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandlerRegistry(DefaultHandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            throw new IllegalArgumentException("Handler registry can't be null");
        }

        this.handlerRegistry = handlerRegistry;
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

    private Session createNewPipelineSession(PipelineDefinition pipelineDefinition, SessionKey sessionKey) {
        if (sessionCache == null) {
            throw new NullPointerException("Session cache is null");
        }

        // TODO: Переделать дефинишены
        final int startStage = pipelineDefinition.getStageDefinitions().stream()
                .map(StageDefinition::getStep)
                .mapToInt(i -> i)
                .min()
                .getAsInt();
        final PipelineSessionInfo pipelineSessionInfo = new PipelineSessionInfo(
                pipelineDefinition,
                pipelineDefinition.getStageDefinitions(),
                startStage
        );
        final DefaultSession session = new DefaultSession(sessionKey, pipelineSessionInfo);

        return sessionCache.putSessionBySessionKey(sessionKey, session);
    }
}
