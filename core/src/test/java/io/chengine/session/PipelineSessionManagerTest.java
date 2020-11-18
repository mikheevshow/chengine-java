package io.chengine.session;

import io.chengine.connector.BotRequest;
import io.chengine.session.pipeline.PipelineSessionManager;
import org.junit.jupiter.api.Test;

import static io.chengine.UtilCommon.createBotRequest;
import static io.chengine.UtilCommon.createEmptyPipeline;
import static org.junit.jupiter.api.Assertions.*;

public class PipelineSessionManagerTest {

    private final PipelineSessionManager pipelineSessionManager = new PipelineSessionManager(new ChengineSessionContext<>());

    @Test
    public void createSessionTest() {
        var request = createBotRequest();
        var pipeline = createEmptyPipeline();
        var session = pipelineSessionManager.createSession(request, pipeline);
        assertAll(
                () -> assertEquals(request.user(), session.user()),
                () -> assertEquals(request.chat(), session.chat()),
                () -> assertEquals(pipeline, session.data()),
                () -> assertEquals(request.apiIdentifier(), session.api()),
                () -> assertNotNull(session.uuid())
        );
    }

    @Test
    public void getNullSessionTest() {
        var request = createBotRequest();
        var session = pipelineSessionManager.getSession(request);
        assertNull(session);
    }

    @Test
    public void getSessionTest() {
        var request = createBotRequest();
        var pipeline = createEmptyPipeline();
        pipelineSessionManager.createSession(request, pipeline);
        var session = pipelineSessionManager.getSession(request);
        assertAll(
                () -> assertEquals(request.user(), session.user()),
                () -> assertEquals(request.chat(), session.chat()),
                () -> assertEquals(pipeline, session.data()),
                () -> assertEquals(request.apiIdentifier(), session.api()),
                () -> assertNotNull(session.uuid())
        );
    }
}
