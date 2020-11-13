package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Supplier;

/**
 * Use this response if you want terminate stage immediately
 *
 * <pre>
 *      &#64;Stage(step=0, name="init-stage")
 *      public StageAction<?> doSomeStage(Message message) {
 *
 *          return StageAction
 *                  .checkStage(() -> "Hello".equals(message.text())))
 *                  .onFailCheckReturn(checkStage -> StageResponse.terminate())
 *                  ....
 *
 *      }
 * </pre>
 */
public class TerminateStageResponse implements StageResponse {

    private Supplier<ActionResponse> actionResponse;

    public TerminateStageResponse() {
    }

    public TerminateStageResponse(Supplier<ActionResponse> actionResponse) {
        this.actionResponse = actionResponse;
    }

    public Supplier<ActionResponse> actionResponse() {
        return actionResponse;
    }
}
