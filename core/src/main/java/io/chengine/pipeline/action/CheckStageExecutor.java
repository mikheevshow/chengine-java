package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.processor.response.ActionResponseHandler;

import javax.annotation.concurrent.ThreadSafe;
import javax.naming.OperationNotSupportedException;

@ThreadSafe
public class CheckStageExecutor<T> extends AbstractStageExecutor<T> {

    public CheckStageExecutor(ActionResponseHandler actionResponseHandler) {
        super(actionResponseHandler);
    }

    @Override
    protected ActionResponse processStage(Executable<T> executable) throws Exception {
        if (!(executable instanceof CheckStageAction)) {
            throw new OperationNotSupportedException("Can't execute actions of class: " + executable.getClass());
        }

        CheckStageAction<T> checkStageAction = (CheckStageAction<T>) executable;

        var check = checkStageAction.check();
        var stageCheck = check.get();

        if (stageCheck.isSuccess()) {
            // TODO Handle success step check
        } else {
            // TODO Handle fail step check
        }

        return null;

    }

}
