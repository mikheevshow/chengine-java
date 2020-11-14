package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.processor.response.ActionResponseHandler;

import javax.annotation.concurrent.ThreadSafe;
import javax.naming.OperationNotSupportedException;
import java.util.function.Supplier;

@ThreadSafe
public class FireAndForgetExecutor<T> extends AbstractStageExecutor<T> {

    public FireAndForgetExecutor(ActionResponseHandler actionResponseHandler) {
        super(actionResponseHandler);
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

        return response.get();
    }
}
