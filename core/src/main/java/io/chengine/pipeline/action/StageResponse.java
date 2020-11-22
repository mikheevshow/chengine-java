package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Supplier;

public interface StageResponse extends ActionResponse {

    static ActionResponse terminate() {
        return new TerminateStageResponse();
    }

    static ActionResponse terminateWithActionResponse(Supplier<ActionResponse> actionResponseSupplier) {
        return new TerminateStageResponse(actionResponseSupplier);
    }

}
