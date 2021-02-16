package io.chengine.pipeline.action;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.action.exception.StageActionExecutionException;

import java.util.function.Supplier;

public class FireStageActionMethodReturnedValueHandler extends AbstractStageActionMethodReturnedValueHandler {

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends StageAction<?>> supports() {
        return (Class<? extends StageAction<?>>) FireStageAction.class;
    }

    @Override
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ActionResponse processStage(StageAction<?> stageAction) throws RuntimeException {
        if (!(stageAction.getClass().isAssignableFrom(FireStageAction.class))) {
            throw new StageActionExecutionException("Can't execute actions of class: " + stageAction.getClass());
        }

        FireStageAction<ActionResponse> fireStageAction = (FireStageAction<ActionResponse>) stageAction;
        Supplier<ActionResponse> response = fireStageAction.response();

        if (response == null) {
            throw new NullPointerException("doAction method has null argument");
        }

        completeStage();
        return response.get();
    }
}
