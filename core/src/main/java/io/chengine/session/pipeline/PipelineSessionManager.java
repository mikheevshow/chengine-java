package io.chengine.session.pipeline;

import io.chengine.connector.BotRequest;
import io.chengine.pipeline.Pipeline;
import io.chengine.session.ChengineSessionContext;
import io.chengine.session.Session;
import io.chengine.session.SessionKey;
import io.chengine.session.SessionManager;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSessionManager implements SessionManager {

    private ChengineSessionContext chengineSessionContext;

    public PipelineSessionManager(ChengineSessionContext chengineSessionContext) {
        this.chengineSessionContext = chengineSessionContext;
    }

    @Override
    public Session getSession(BotRequest request) {
        var sessionKey = createSessionKey(request);
        return chengineSessionContext.getSessionBySessionKey(sessionKey);
    }

    @Override
    public Session getSession(SessionKey sessionKey) {
        return chengineSessionContext.getSessionBySessionKey(sessionKey);
    }

    @Override
    public Session getCurrentSession() {
        return null; //todo ThreadLocal
    }

    @Override
    public Session createSession(BotRequest request, Pipeline pipeline) {
        var sessionKey = createSessionKey(request);
        var pipelineSession = new PipelineSession(
            UUID.randomUUID(),
            pipeline,
            request.user(),
            5,
            TimeUnit.MINUTES,
            0,
            false
        );

        if(chengineSessionContext.getSessionBySessionKey(sessionKey) != null) {
            throw new RuntimeException("Session already exist " + sessionKey);
        }

        return chengineSessionContext.putSessionBySessionKey(sessionKey, pipelineSession);
    }

    @Override
    public void invalidateSession(SessionKey sessionKey) {
        chengineSessionContext.invalidateCacheBySessionKey(sessionKey);
    }

    @Override
    public void invalidateCurrentSession() {
        //todo ThreadLocal
    }

    private SessionKey createSessionKey(BotRequest request) {
        return new SessionKey(
            request.apiIdentifier().identifier(),
            request.user().getId(),
            request.chat().id());
    }
}
