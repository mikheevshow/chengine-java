package io.chengine.session;

import io.chengine.connector.BotRequestContext;

import javax.annotation.Nullable;
import java.util.UUID;

public interface SessionManager {

    /**
     * Gets session based on user, chat and bot api identifier
     *
     * @param sessionKey - specific {@link SessionKey}
     * @return {@link Session} object
     */
    @Nullable
    Session getSession(SessionKey sessionKey);

    /**
     * Creates a new session
     *
     * @param request - bot request
     * @param data - custom data
     * @return {@link Session} object or null
     * @see UserSessionContextHolder
     */
    <T> Session createSession(BotRequestContext request, T data);

    void invalidateSession(SessionKey sessionKey);

    void invalidateSessionByUuid(UUID uuid);

    void invalidateCurrentSession();

}
