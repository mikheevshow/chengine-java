package io.chengine.session;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.time.Duration;

public class SessionContext implements SessionCache {

    private final static Logger log = LogManager.getLogger(SessionContext.class);

    private final Cache<SessionKey, Session> cache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(5))
            .build();

    @Nullable
    public Session getSessionBySessionKey(SessionKey sessionKey) {
        try {
            return cache.getIfPresent(sessionKey);
        } catch (NullPointerException e) {
            log.error("Session not exist by sessionKey {}", sessionKey, e);
            return null;
        }
    }

    public Session putSessionBySessionKey(SessionKey sessionKey, Session session) {
        cache.put(sessionKey, session);
        return getSessionBySessionKey(sessionKey);
    }

    public void invalidateCacheBySessionKey(SessionKey sessionKey) {
        if(getSessionBySessionKey(sessionKey) != null) {
            cache.invalidate(sessionKey);
        }
    }

}
