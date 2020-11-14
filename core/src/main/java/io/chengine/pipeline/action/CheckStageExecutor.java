package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.processor.response.ActionResponseHandler;

import javax.annotation.concurrent.ThreadSafe;
import javax.naming.OperationNotSupportedException;
import java.util.Objects;

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
        Objects.requireNonNull(stageCheck, "Null found instead of StageCheck object. Terminate the pipeline.");

        ActionResponse actionResponse;

        if (stageCheck.isSuccess()) {

            if (checkStageAction.successStageCheckActionResponse() != null) {
                actionResponse = checkStageAction.successStageCheckActionResponse().apply(stageCheck);
            } else if (checkStageAction.successActionResponse() != null) {
                actionResponse = checkStageAction.successActionResponse().get();
            } else {
                throw new MethodNotUsedException("No one success handling method use. Terminate pipeline.");
            }

            completeStage();

        } else {

            if (checkStageAction.failStageCheckActionResponseReturn() != null) {
                actionResponse = checkStageAction.failStageCheckActionResponseReturn().apply(stageCheck);
            } else if (checkStageAction.failActionResponseReturn() != null) {
                actionResponse = checkStageAction.failActionResponseReturn().get();
            } else if (checkStageAction.failStageCheckActionResponseResume() != null) {
                actionResponse = checkStageAction.failStageCheckActionResponseResume().apply(stageCheck);
                completeStage();
            } else if (checkStageAction.failActionResponseResume() != null) {
                actionResponse = checkStageAction.failActionResponseResume().get();
                completeStage();
            } else if (checkStageAction.failStageCheckActionResponseTerminate() != null) {
                actionResponse = checkStageAction.failStageCheckActionResponseTerminate().apply(stageCheck);
                terminateSession();
            } else {
                throw new MethodNotUsedException("No one fail handling method use. Terminate pipeline.");
            }

        }

        return actionResponse;

    }

}
