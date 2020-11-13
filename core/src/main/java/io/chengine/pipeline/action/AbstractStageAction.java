package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.pipeline.exec.Executor;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public abstract class AbstractStageAction<T> implements StageAction<T> {

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        return this;
    }

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error) {
        return this;
    }

    @Override
    public StageAction<T> onErrorResume(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        return this;
    }

    @Override
    public void executeOn(Executor executor) {
        executor.execute(this);
    }
}
