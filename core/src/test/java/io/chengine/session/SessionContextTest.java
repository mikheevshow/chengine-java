//package io.chengine.session;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static io.chengine.UtilCommon.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SessionContextTest {
//
//    private final ChengineSessionContext<UserPipelineSessionInfo> sessionContext = new ChengineSessionContext<>();
//
//    @Test
//    public void putSessionBySessionKeyTest() {
//        var sessionKey = createSessionKey("telegram");
//        var session = createPipelineSession();
//        sessionContext.putSessionBySessionKey(sessionKey, session);
//
//        var currentSession = sessionContext.getSessionBySessionKey(sessionKey);
//        assertEquals(session, currentSession);
//    }
//
//    @Test
//    public void invalidateSessionTest() {
//        var sessionKey = createSessionKey("telegram");
//        var session = createPipelineSession();
//        sessionContext.putSessionBySessionKey(sessionKey, session);
//
//        var currentSession = sessionContext.getSessionBySessionKey(sessionKey);
//        assertEquals(session, currentSession);
//
//        sessionContext.invalidateCacheBySessionKey(sessionKey);
//
//        var afterValidateSession = sessionContext.getSessionBySessionKey(sessionKey);
//        assertNull(afterValidateSession);
//    }
//
//    @Test
//    public void getConcurrentMapTest() {
//        var sessionKeyTg = createSessionKey("telegram");
//        var sessionKeyVk = createSessionKey("vk");
//        var sessionTg = createPipelineSession();
//        var sessionVk = createPipelineSession();
//        sessionContext.putSessionBySessionKey(sessionKeyTg, sessionTg);
//        sessionContext.putSessionBySessionKey(sessionKeyVk, sessionVk);
//
//        assertAll(
//            () -> assertTrue(sessionContext.getConcurrentMap().containsKey(sessionKeyTg)),
//            () -> assertTrue(sessionContext.getConcurrentMap().containsKey(sessionKeyVk)),
//            () -> assertTrue(sessionContext.getConcurrentMap().containsValue(sessionTg)),
//            () -> assertTrue(sessionContext.getConcurrentMap().containsValue(sessionVk))
//        );
//    }
//}
