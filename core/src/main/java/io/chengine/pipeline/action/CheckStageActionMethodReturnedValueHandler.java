package io.chengine.pipeline.action;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.action.exception.MethodNotUsedException;
import io.chengine.pipeline.action.exception.StageActionExecutionException;

import java.util.Objects;

public class CheckStageActionMethodReturnedValueHandler extends AbstractStageActionMethodReturnedValueHandler {

    @Override
    public Class<? extends StageAction> supports() {
        return CheckStageAction.class;
    }

    @Override
    protected boolean isAllowToProcess(
            HandlerMethod handlerMethod,
            BotRequestContext request,
            BotResponseContext response) {

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ActionResponse processStage(StageAction<?> stageAction) throws RuntimeException {
        if (!(stageAction.getClass().isAssignableFrom(CheckStageAction.class))) {
            throw new StageActionExecutionException("Can't execute actions of class: " + stageAction.getClass());
        }

        CheckStageAction<ActionResponse> checkStageAction = (CheckStageAction<ActionResponse>) stageAction;

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
                throw new StageActionExecutionException("No one success handling method use. Terminate pipeline.");
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
