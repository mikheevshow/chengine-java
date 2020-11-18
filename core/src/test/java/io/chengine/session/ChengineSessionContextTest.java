package io.chengine.session;

import org.junit.jupiter.api.Test;

import static io.chengine.UtilCommon.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChengineSessionContextTest {

    private final ChengineSessionContext<UserPipelineSessionInfo> chengineSessionContext = new ChengineSessionContext<UserPipelineSessionInfo>();

    //test for test commit
    @Test
    public void putSessionBySessionKeyTest() {
        var sessionKey = createSessionKey("telegram");
        var session = createPipelineSession();
        chengineSessionContext.putSessionBySessionKey(sessionKey, session);

        var currentSession = chengineSessionContext.getSessionBySessionKey(sessionKey);
        assertEquals(session, currentSession);
    }

    @Test
    public void invalidateSessionTest() {
        var sessionKey = createSessionKey("telegram");
        var session = createPipelineSession();
        chengineSessionContext.putSessionBySessionKey(sessionKey, session);

        var currentSession = chengineSessionContext.getSessionBySessionKey(sessionKey);
        assertEquals(session, currentSession);

        chengineSessionContext.invalidateCacheBySessionKey(sessionKey);

        var afterValidateSession = chengineSessionContext.getSessionBySessionKey(sessionKey);
        assertNull(afterValidateSession);
    }

    @Test
    public void getConcurrentMapTest() {
        var sessionKeyTg = createSessionKey("telegram");
        var sessionKeyVk = createSessionKey("vk");
        var sessionTg = createPipelineSession();
        var sessionVk = createPipelineSession();
        chengineSessionContext.putSessionBySessionKey(sessionKeyTg, sessionTg);
        chengineSessionContext.putSessionBySessionKey(sessionKeyVk, sessionVk);

        assertAll(
            () -> assertTrue(chengineSessionContext.getConcurrentMap().containsKey(sessionKeyTg)),
            () -> assertTrue(chengineSessionContext.getConcurrentMap().containsKey(sessionKeyVk)),
            () -> assertTrue(chengineSessionContext.getConcurrentMap().containsValue(sessionTg)),
            () -> assertTrue(chengineSessionContext.getConcurrentMap().containsValue(sessionVk))
        );
    }
}
