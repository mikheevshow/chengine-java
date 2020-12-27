//package io.chengine.session.pipeline;
//
//import io.chengine.session.*;
//
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//public class PipelineSessionManager implements SessionManager<UserPipelineSessionInfo> {
//
//    private final SessionCache<UserPipelineSessionInfo> chengineSessionContext;
//
//    public PipelineSessionManager(SessionCache<UserPipelineSessionInfo> chengineSessionContext) {
//        this.chengineSessionContext = chengineSessionContext;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Session<UserPipelineSessionInfo> getSession(BotRequest request) {
//        var sessionKey = createSessionKey(request);
//        return chengineSessionContext.getSessionBySessionKey(sessionKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Session<UserPipelineSessionInfo> getSession(SessionKey sessionKey) {
//        return chengineSessionContext.getSessionBySessionKey(sessionKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public Session<UserPipelineSessionInfo> getCurrentSession() {
//        return (Session<UserPipelineSessionInfo>) UserSessionContextHolder.getSession();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Session<UserPipelineSessionInfo> createSession(BotRequest request, UserPipelineSessionInfo userPipelineSessionInfo) {
//        var sessionKey = createSessionKey(request);
//        var pipelineSession = new PipelineSession(
//                UUID.randomUUID(),
//                userPipelineSessionInfo,
//                request.user(),
//                request.chat(),
//                request.apiIdentifier(),
//                5,
//                TimeUnit.MINUTES,
//                0,
//                false
//        );
//
//        if (chengineSessionContext.getSessionBySessionKey(sessionKey) != null) {
//            throw new RuntimeException("Session already exist " + sessionKey);
//        }
//
//        var session = chengineSessionContext.putSessionBySessionKey(sessionKey, pipelineSession);
//        UserSessionContextHolder.setSession(session);
//        return session;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void invalidateSession(SessionKey sessionKey) {
//        chengineSessionContext.invalidateCacheBySessionKey(sessionKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void invalidateSessionByUuid(UUID uuid) {
//        chengineSessionContext
//                .getConcurrentMap()
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue().uuid().equals(uuid))
//                .map(Map.Entry::getKey)
//                .findAny()
//                .ifPresent(this::invalidateSession);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void invalidateCurrentSession() {
//        var session = UserSessionContextHolder.getSession();
//        UserSessionContextHolder.setSession(null);
//        invalidateSessionByUuid(session.uuid());
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    private SessionKey createSessionKey(BotRequest request) {
//        return new SessionKey(
//                request.apiIdentifier().identifier(),
//                request.user().getId(),
//                request.chat().id());
//    }
//}
