//package io.chengine.session;
//
//import com.github.benmanes.caffeine.cache.Cache;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.annotation.Nullable;
//import java.time.Duration;
//import java.util.concurrent.ConcurrentMap;
//
//public class ChengineSessionContext<T> implements SessionCache<T> {
//
//    private final static Logger log = LogManager.getLogger(ChengineSessionContext.class);
//
//    private final Cache<SessionKey, Session<T>> cache = Caffeine
//            .newBuilder()
//            .expireAfterWrite(Duration.ofMinutes(5))
//            .build();
//
//    @Nullable
//    public Session<T> getSessionBySessionKey(SessionKey sessionKey) {
//        try {
//            return cache.getIfPresent(sessionKey);
//        } catch (NullPointerException e) {
//            log.error("Session not exist by sessionKey {}", sessionKey, e);
//            return null;
//        }
//    }
//
//    public Session<T> putSessionBySessionKey(SessionKey sessionKey, Session<T> session) {
//        cache.put(sessionKey, session);
//        return getSessionBySessionKey(sessionKey);
//    }
//
//    public void invalidateCacheBySessionKey(SessionKey sessionKey) {
//        if(getSessionBySessionKey(sessionKey) != null) {
//            cache.invalidate(sessionKey);
//        }
//    }
//
//    public ConcurrentMap<SessionKey, Session<T>> getConcurrentMap() {
//        return cache.asMap();
//    }
//}
