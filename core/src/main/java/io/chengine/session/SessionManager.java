//package io.chengine.session;
//
//import javax.annotation.Nullable;
//import java.util.UUID;
//
//public interface SessionManager<T> {
//
//    /**
//     * Gets session based on user, chat and bot api identifier
//     *
//     * @param request - bot request
//     * @return {@link Session} object
//     */
//    Session<T> getSession(BotRequest request);
//
//    /**
//     * Gets session based on user, chat and bot api identifier
//     *
//     * @param sessionKey - specific {@link SessionKey}
//     * @return {@link Session} object
//     */
//    Session<T> getSession(SessionKey sessionKey);
//
//    /**
//     * Gets session of current user
//     *
//     * @return {@link Session} object or null
//     * @see UserSessionContextHolder
//     */
//    @Nullable
//    Session<T> getCurrentSession();
//
//    /**
//     * Creates a new session
//     *
//     * @param request - bot request
//     * @param data - custom data
//     * @return {@link Session} object or null
//     * @see UserSessionContextHolder
//     */
//    Session<T> createSession(BotRequest request, T data);
//
//    void invalidateSession(SessionKey sessionKey);
//
//    void invalidateSessionByUuid(UUID uuid);
//
//    void invalidateCurrentSession();
//
//}
