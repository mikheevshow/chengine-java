package io.chengine.pipeline;

import io.chengine.message.Send;
import io.chengine.pipeline.action.FireAndForgetExecutor;
import io.chengine.pipeline.action.StageAction;
import io.chengine.session.pipeline.PipelineSessionManager;
import org.junit.jupiter.api.Test;

import static io.chengine.UtilCommon.createPipelineSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StageActionTest {

    @Test
    public void testDoActionOne() {

        var messageText = "Hello Misha";

        var pipelineSessionManagerMock = mock(PipelineSessionManager.class);
        var stageAction = StageAction.doAction(() -> Send.messageWithText(() -> messageText));
        stageAction.executeOn(new FireAndForgetExecutor<>(pipelineSessionManagerMock));
        when(pipelineSessionManagerMock.getCurrentSession()).thenAnswer(invocationOnMock -> createPipelineSession());
        var actionResponse = stageAction.execute();
        var send = (Send) actionResponse;
        assertEquals(messageText, send.text());
    }

}
