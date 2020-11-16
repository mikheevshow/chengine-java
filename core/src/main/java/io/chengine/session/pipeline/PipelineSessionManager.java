package io.chengine.session.pipeline;

import io.chengine.connector.BotRequest;
import io.chengine.pipeline.Pipeline;
import io.chengine.session.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSessionManager implements SessionManager {

    private final ChengineSessionContext chengineSessionContext;

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
        return SessionUserContext.getSession();
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
    public void invalidateSessionByUuid(UUID uuid) {
        var sessionKey = chengineSessionContext.getConcurrentMap()
            .entrySet().stream()
            .filter(entry -> entry.getValue().getSessionUuid().equals(uuid))
            .map(Map.Entry::getKey)
            .findAny();

        sessionKey.ifPresent(this::invalidateSession);
    }

    @Override
    public void invalidateCurrentSession() {
        var session = SessionUserContext.getSession();
        SessionUserContext.setSession(null);
        invalidateSessionByUuid(session.getSessionUuid());
    }

    private SessionKey createSessionKey(BotRequest request) {
        return new SessionKey(
            request.apiIdentifier().identifier(),
            request.user().getId(),
            request.chat().id());
    }
}
