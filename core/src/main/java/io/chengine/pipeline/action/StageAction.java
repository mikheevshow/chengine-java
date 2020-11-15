package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public interface StageAction<T> extends Executable<T> {

    // ==============================================================================================================
    //	 Static Generators
    // ==============================================================================================================

    /**
     * Creates a {@link FireStageAction}
     *
     * @param response - an {@link ActionResponse} supplier
     * @return {@link FireStageAction}
     */
    static <T> StageAction<T> doAction(Supplier<ActionResponse> response) {
        return new FireStageAction<>(response);
    }

    /**
     * Creates a {@link CheckStageAction}
     *
     * @param check - a {@link StageCheck} supplier
     * @return {@link CheckStageAction}
     */
    static <T> CheckStageAction<T> checkStage(Supplier<StageCheck<T>> check) {
        return new CheckStageAction<>(check);
    }

    // ==============================================================================================================
    //	 Error handling methods
    // ==============================================================================================================

    StageAction<T> onErrorTerminate(Consumer<Throwable> error, Supplier<ActionResponse> response);

    StageAction<T> onErrorTerminate(Consumer<Throwable> error);

    StageAction<T> onErrorResume(Consumer<Throwable> error, Supplier<ActionResponse> response);

    StageAction<T> onErrorReturn(Consumer<Throwable> error, Supplier<ActionResponse> response);

}
