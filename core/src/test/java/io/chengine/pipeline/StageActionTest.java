package io.chengine.pipeline;

import io.chengine.FailTestException;
import io.chengine.message.Send;
import io.chengine.pipeline.action.*;
import io.chengine.pipeline.action.exception.StageActionAssemblyException;
import io.chengine.pipeline.action.exception.StageActionExecutionException;
import io.chengine.session.pipeline.PipelineSessionManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.chengine.UtilCommon.createPipelineSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StageActionTest {

    @Mock
    private PipelineSessionManager pipelineSessionManager;

    @Mock
    private Logger logger;

    private FireAndForgetExecutor<Object> fireAndForgetExecutor;
    private CheckStageExecutor<Object> checkStageExecutor;

    @BeforeEach
    public void before() {
        fireAndForgetExecutor = new FireAndForgetExecutor<>(pipelineSessionManager);
        checkStageExecutor = new CheckStageExecutor<>(pipelineSessionManager);
    }


    @Test
    @DisplayName("Testing `doAction` block calling which throws an exception without `onError` handle block")
    public void testDoActionThrowExceptionWithoutOnErrorBlock() {

        var stageAction = StageAction.doAction(() -> {
            throw new FailTestException();
        });

        stageAction.executeOn(fireAndForgetExecutor);

        assertThrows(StageActionExecutionException.class, stageAction::execute);
    }

    @Test
    @DisplayName("Testing of `onError` methods multiply usage")
    public void testMultiplyOnErrorBlockUsing() {

        var stageActionList = List.of(

                StageAction
                        .doAction(Send::message)
                        .onErrorTerminate(System.out::println, Send::message)
                        .onErrorTerminate(System.out::println),

                StageAction
                        .doAction(Send::new)
                        .onErrorTerminate(System.out::println, Send::message)
                        .onErrorResume(System.out::println, Send::message),

                StageAction
                        .doAction(Send::new)
                        .onErrorTerminate(System.out::println, Send::message)
                        .onErrorReturn(System.out::println, Send::message),

                StageAction
                        .doAction(Send::message)
                        .onErrorTerminate(System.out::println)
                        .onErrorResume(System.out::println, Send::message),

                StageAction
                        .doAction(Send::message)
                        .onErrorTerminate(System.out::println)
                        .onErrorReturn(System.out::println, Send::message),

                StageAction
                        .doAction(Send::message)
                        .onErrorResume(System.out::println, Send::message)
                        .onErrorReturn(System.out::println, Send::message)

        );

        stageActionList.forEach(stageAction -> {
            stageAction.executeOn(fireAndForgetExecutor);
            assertThrows(StageActionAssemblyException.class, stageAction::execute);
        });

    }


    @Test
    @DisplayName("Test `doAction` method with normal condition, message with text only assertion")
    public void testDoActionOne() {

        var messageText = "Hello Misha";

        var stageAction = StageAction.doAction(() -> Send.messageWithText(() -> messageText));

        stageAction.executeOn(fireAndForgetExecutor);

        when(pipelineSessionManager.getCurrentSession()).thenAnswer(invocationOnMock -> createPipelineSession());

        var actionResponse = stageAction.execute();
        var send = (Send) actionResponse;

        assertEquals(messageText, send.text());
    }

    @Test
    @DisplayName("Throw an exception in `checkStage` method")
    public void testThrowExceptionInCheckStage() {

        var failMessageText = "FAILED_MESSAGE";

        var stageAction = StageAction
                .checkStage(() -> {
                    throw new RuntimeException("Fail");
                })
                .onErrorReturn(ex -> logger.info(ex), () -> Send.messageWithText(() -> failMessageText));

        stageAction.executeOn(checkStageExecutor);

        var actionResponse = stageAction.execute();
        var send = (Send) actionResponse;

        assertEquals(failMessageText, send.text());
    }

    @Test
    @DisplayName("Throw an exception in `onError` block")
    public void testThrowExceptionInErrorBlock() {

        var stageAction = StageAction
                .checkStage(StageCheck::success)
                .onSuccessCheckReturn(Send::message)
                .onFailCheckReturn(Send::message)
                .onErrorReturn(ex -> logger.info(ex), () -> {
                    throw new FailTestException(":(");
                });

        stageAction.executeOn(checkStageExecutor);

        assertThrows(FailTestException.class, stageAction::execute);
    }
}
