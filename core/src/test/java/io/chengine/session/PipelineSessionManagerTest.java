//package io.chengine.session;
//
//import io.chengine.session.pipeline.PipelineSessionManager;
//import org.junit.jupiter.api.Test;
//
//import static io.chengine.UtilCommon.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PipelineSessionManagerTest {
//
//    private final PipelineSessionManager pipelineSessionManager = new PipelineSessionManager(new ChengineSessionContext<>());
//
//    @Test
//    public void createSessionTest() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        var session = pipelineSessionManager.createSession(request, pipeline);
//        assertAll(
//                () -> assertEquals(request.user(), session.user()),
//                () -> assertEquals(request.chat(), session.chat()),
//                () -> assertEquals(pipeline, session.data()),
//                () -> assertEquals(request.apiIdentifier(), session.api()),
//                () -> assertNotNull(session.uuid())
//        );
//    }
//
//    @Test
//    public void getNullSessionTest() {
//        var request = createBotRequest();
//        var session = pipelineSessionManager.getSession(request);
//        assertNull(session);
//    }
//
//    @Test
//    public void getSessionTest() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        pipelineSessionManager.createSession(request, pipeline);
//        var session = pipelineSessionManager.getSession(request);
//        assertAll(
//                () -> assertEquals(request.user(), session.user()),
//                () -> assertEquals(request.chat(), session.chat()),
//                () -> assertEquals(pipeline, session.data()),
//                () -> assertEquals(request.apiIdentifier(), session.api()),
//                () -> assertNotNull(session.uuid())
//        );
//    }
//
//    @Test
//    public void getSessionKeyTest() {
//        var sessionKey1 = createSessionKey("telegram");
//        var sessionKey2 = createSessionKey("telegram");
//        var session1 = pipelineSessionManager.getSession(sessionKey1);
//        var session2 = pipelineSessionManager.getSession(sessionKey2);
//        assertAll(
//                () -> assertEquals(session1, session2)
//        );
//    }
//
//    @Test
//    public void invalidateSessionByUuidTest() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        pipelineSessionManager.createSession(request, pipeline);
//        var sessionBefore = pipelineSessionManager.getSession(request);
//        assertNotNull(sessionBefore);
//        pipelineSessionManager.invalidateSessionByUuid(sessionBefore.uuid());
//        var sessionAfter = pipelineSessionManager.getSession(request);
//        assertNull(sessionAfter);
//    }
//
//    @Test
//    public void getCurrentSession() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        assertNull(pipelineSessionManager.getCurrentSession());
//        pipelineSessionManager.createSession(request, pipeline);
//        assertNotNull(pipelineSessionManager.getCurrentSession());
//    }
//
//    @Test
//    public void invalidateCurrentSession() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        pipelineSessionManager.createSession(request, pipeline);
//        var currentSessionBefore = pipelineSessionManager.getCurrentSession();
//        assertNotNull(currentSessionBefore);
//        pipelineSessionManager.invalidateCurrentSession();
//        var currentSessionAfter = pipelineSessionManager.getCurrentSession();
//        assertNull(currentSessionAfter);
//    }
//
//    @Test
//    public void sessionAlreadyExist() {
//        var request = createBotRequest();
//        var pipeline = createEmptyPipeline();
//        pipelineSessionManager.createSession(request, pipeline);
//        assertThrows(RuntimeException.class, () -> pipelineSessionManager.createSession(request, pipeline));
//    }
//}
