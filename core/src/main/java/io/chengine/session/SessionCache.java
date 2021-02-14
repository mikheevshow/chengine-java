package io.chengine.session;

import javax.annotation.Nullable;

public interface SessionCache {

    @Nullable
    Session getSessionBySessionKey(SessionKey sessionKey);

    Session putSessionBySessionKey(SessionKey sessionKey, Session session);

    void invalidateCacheBySessionKey(SessionKey sessionKey);

}
