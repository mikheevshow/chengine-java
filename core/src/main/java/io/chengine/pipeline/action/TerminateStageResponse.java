package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Supplier;

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
