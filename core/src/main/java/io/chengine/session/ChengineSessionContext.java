package io.chengine.session;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public class ChengineSessionContext {
    private final Cache<SessionKey, Session> cache = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .build();

    @Nullable
    public Session getSessionBySessionKey(SessionKey sessionKey) {
        return cache.get(sessionKey, session -> (Session) session);
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

    public ConcurrentMap<SessionKey, Session> getConcurrentMap() {
        return cache.asMap();
    }
}
