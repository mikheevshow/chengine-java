package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.pipeline.exec.Executor;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public class CheckStageAction<T> extends AbstractStageAction<T> {

    private final Supplier<StageCheck<T>> check;

    protected CheckStageAction(Supplier<StageCheck<T>> check) {
        this.check = check;
    }

    public CheckStageAction<T> onSuccessCheckReturn(Function<StageCheck<T>, ActionResponse> success) {
        return this;
    }

    public CheckStageAction<T> onSuccessCheckReturn(Supplier<ActionResponse> response) {
        return this;
    }

    public CheckStageAction<T> onFailCheckReturn(Function<StageCheck<T>, ActionResponse> fail) {
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message. Stage index for current user doesn't change.
     *
     * @param response - a supplier which returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckReturn(Supplier<ActionResponse> response) {
        return this;
    }

    /**
     * Terminates a pipeline if {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message before termination
     *
     * @param fail - a function which receive {@link CheckStageAction#check} result and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckTerminate(Function<StageCheck<T>, ActionResponse> fail) {
        return this;
    }

    Supplier<StageCheck<T>> check() {
        return check;
    }

    @Override
    public void executeOn(Executor executor) {

    }
}
