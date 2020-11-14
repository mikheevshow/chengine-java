package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Supplier;

/**
 * Stage action which uses fire and forget response strategy.
 * Must be use in the first step of a pipeline {@link io.chengine.annotation.HandlerType#PIPELINE}
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
    public void execute() {
        this.executeOn(Executors.fire());
    }
}
