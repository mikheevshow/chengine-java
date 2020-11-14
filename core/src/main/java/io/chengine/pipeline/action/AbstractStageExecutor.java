package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.processor.response.ActionResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractStageExecutor<T> implements Executor<T> {

    protected static final Logger log = LogManager.getLogger(AbstractStageExecutor.class);

    private final ActionResponseHandler actionResponseHandler;

    public AbstractStageExecutor(ActionResponseHandler actionResponseHandler) {
        this.actionResponseHandler = actionResponseHandler;
    }

    @Override
    public void execute(Executable<T> executable) {

        try {
            final ActionResponse actionResponse = processStage(executable);
            actionResponseHandler.handle(actionResponse);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);

            final AbstractStageAction<T> stageAction = (AbstractStageAction<T>) executable;
            final Consumer<Throwable> throwableConsumer = stageAction.errorConsumer();
            Objects.requireNonNull(throwableConsumer, "Error handling consumer can't be null");

            if (stageAction.errorActionResponseReturn() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                actionResponseHandler.handle(actionResponse.get());
            } else if (stageAction.errorActionResponseResume() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                completeStage();
                actionResponseHandler.handle(actionResponse.get());
            } else if (stageAction.errorActionResponseTerminate() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                terminateSession();
                actionResponseHandler.handle(actionResponse.get());
            }

            terminateSession();
            throw new NullPointerException("No one error handling method use. Terminate pipeline.");
        }
    }

    protected abstract ActionResponse processStage(Executable<T> executable) throws Exception;

    protected void completeStage() {
        // TODO add session update here
    }

    protected void terminateSession() {
        // TODO add session termination here
    }

}
