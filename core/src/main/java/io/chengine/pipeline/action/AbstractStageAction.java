package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.pipeline.action.exception.StageActionAssemblyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public abstract class AbstractStageAction<T> implements StageAction<T> {

    private final Logger log = LogManager.getLogger(AbstractStageAction.class);

    // ==============================================================================================================
    //	 Terminate Functions
    // ==============================================================================================================

    protected Consumer<Throwable> errorConsumer;

    protected Supplier<ActionResponse> errorActionResponseTerminate;
    protected Supplier<ActionResponse> errorActionResponseResume;
    protected Supplier<ActionResponse> errorActionResponseReturn;

    // ==============================================================================================================
    //	 On Error Methods Usage Flags
    // ==============================================================================================================

    private boolean onErrorTerminateThrowableConsumer = false;
    private boolean onErrorTerminateThrowableConsumerActionResponseSupplier = false;
    private boolean onErrorResumeThrowableConsumerActionResponseSupplier = false;
    private boolean onErrorReturnThrowableConsumerActionResponseSupplier = false;

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseTerminate = response;
        this.onErrorTerminateThrowableConsumerActionResponseSupplier = true;
        return this;
    }

    @Override
    public StageAction<T> onErrorTerminate(Consumer<Throwable> error) {
        this.errorConsumer = error;
        this.onErrorTerminateThrowableConsumer = true;
        return this;
    }

    @Override
    public StageAction<T> onErrorResume(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseResume = response;
        this.onErrorResumeThrowableConsumerActionResponseSupplier = true;
        return this;
    }

    @Override
    public StageAction<T> onErrorReturn(Consumer<Throwable> error, Supplier<ActionResponse> response) {
        this.errorConsumer = error;
        this.errorActionResponseReturn = response;
        this.onErrorReturnThrowableConsumerActionResponseSupplier = true;
        return this;
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

    // ==============================================================================================================
    //	 On Assembly Methods Group
    // ==============================================================================================================

    protected abstract void onPartialAssembly();

    void onAssembly() {
        onPartialAssembly();
        onErrorBlockAssembly();
    }

    private void onErrorBlockAssembly() {
        if (multiplyOnErrorMethodUsage()) {
            throw new StageActionAssemblyException("Multiple use of `onError` methods detected, please use just one of them");
        }
        if (errorConsumer == null) {
            log.info("Exception consumer is not defined, exceptions which throws in other blocks will be rethrown with session termination");
        }
    }

    private boolean multiplyOnErrorMethodUsage() {
        return (onErrorTerminateThrowableConsumer && onErrorTerminateThrowableConsumerActionResponseSupplier) ||
                (onErrorTerminateThrowableConsumer && onErrorResumeThrowableConsumerActionResponseSupplier) ||
                (onErrorTerminateThrowableConsumer && onErrorReturnThrowableConsumerActionResponseSupplier) ||
                (onErrorTerminateThrowableConsumerActionResponseSupplier && onErrorResumeThrowableConsumerActionResponseSupplier) ||
                (onErrorTerminateThrowableConsumerActionResponseSupplier && onErrorReturnThrowableConsumerActionResponseSupplier) ||
                (onErrorResumeThrowableConsumerActionResponseSupplier && onErrorReturnThrowableConsumerActionResponseSupplier);
    }
}
