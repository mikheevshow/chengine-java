package io.chengine.session;

import io.chengine.connector.BotRequest;
import io.chengine.pipeline.Pipeline;
import io.chengine.session.Session;
import io.chengine.session.SessionKey;

import java.util.UUID;

public interface SessionManager {

    /**
     * Gets session based on user, chat and bot api identifier
     *
     * @param request - bot request
     * @return {@link Session} object
     */
    Session getSession(BotRequest request);

    Session getSession(SessionKey sessionKey);

    Session getCurrentSession();

    Session createSession(BotRequest request, Pipeline pipeline);

    void invalidateSession(SessionKey sessionKey);

    void invalidateSessionByUuid(UUID uuid);

    void invalidateCurrentSession();

}
