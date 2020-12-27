//package io.chengine.session;
//
//import javax.annotation.Nullable;
//import java.util.concurrent.ConcurrentMap;
//
//public interface SessionCache<T> {
//
//    @Nullable
//    Session<T> getSessionBySessionKey(SessionKey sessionKey);
//
//    Session<T> putSessionBySessionKey(SessionKey sessionKey, Session<T> session);
//
//    void invalidateCacheBySessionKey(SessionKey sessionKey);
//
//    ConcurrentMap<SessionKey, Session<T>> getConcurrentMap();
//
//}
