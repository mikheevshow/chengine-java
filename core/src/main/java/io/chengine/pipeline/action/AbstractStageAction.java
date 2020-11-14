package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public abstract class AbstractStageAction<T> implements StageAction<T> {

    // ==============================================================================================================
    //	 Terminate Functions
    // ==============================================================================================================

    protected Consumer<Throwable> errorConsumer;

    protected Supplier<ActionResponse> errorActionResponseTerminate;
    protected Supplier<ActionResponse> errorActionResponseResume;
    protected Supplier<ActionResponse> errorActionResponseReturn;

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseTerminate = response;
        return this;
    }

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error) {
        this.errorConsumer = error;
        return this;
    }

    @Override
    public StageAction<T> onErrorResume(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseResume = response;
        return this;
    }

    @Override
    public StageAction<T> onErrorReturn(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseReturn = response;
        return this;
    }

    @Override
    public void executeOn(Executor<T> executor) {
        executor.execute(this);
    }

    Consumer<Throwable> errorConsumer() {
        return errorConsumer;
    }

    Supplier<ActionResponse> errorActionResponseTerminate() {
        return errorActionResponseTerminate;
    }

    Supplier<ActionResponse> errorActionResponseResume() {
        return errorActionResponseResume;
    }

    public Supplier<ActionResponse> errorActionResponseReturn() {
        return errorActionResponseReturn;
    }
}
