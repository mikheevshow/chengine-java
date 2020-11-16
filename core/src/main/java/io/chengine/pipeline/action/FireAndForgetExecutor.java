package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.session.pipeline.PipelineSessionManager;

import javax.annotation.concurrent.ThreadSafe;
import javax.naming.OperationNotSupportedException;
import java.util.function.Supplier;

@ThreadSafe
public class FireAndForgetExecutor<T> extends AbstractStageExecutor<T> {

    public FireAndForgetExecutor(PipelineSessionManager pipelineSessionManager) {
        super(pipelineSessionManager);
    }

    @Override
    protected ActionResponse processStage(Executable<T> executable) throws Exception {

        if (!(executable instanceof FireStageAction)) {
            throw new OperationNotSupportedException("Can't process");
        }

        FireStageAction<T> stageAction = (FireStageAction<T>) executable;
        Supplier<ActionResponse> response = stageAction.response();

        if (response == null) {
            throw new NullPointerException("doAction method has null argument");
        }

        completeStage();
        return response.get();
    }
}
