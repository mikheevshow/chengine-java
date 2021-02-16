package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.pipeline.action.exception.StageActionAssemblyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Stage action which uses fire and forget response strategy.
 * by calling {@link StageAction#doAction(Supplier)} method.
 *
 * @param <T> - a context object for passing data between chain methods
 */
public class FireStageAction<T> extends AbstractStageAction<T> {

    private final Supplier<ActionResponse> response;

    protected FireStageAction(Supplier<ActionResponse> response) {
        this.response = response;
    }

    public Supplier<ActionResponse> response() {
        return response;
    }

    @Override
    protected void onPartialAssembly() {
        if (response == null) {
            throw new StageActionAssemblyException("Passed `null` argument in doAction method");
        }
    }
}
