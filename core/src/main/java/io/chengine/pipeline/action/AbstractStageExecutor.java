package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;
import io.chengine.session.Session;
import io.chengine.session.UserPipelineSessionInfo;
import io.chengine.session.pipeline.PipelineSession;
import io.chengine.session.pipeline.PipelineSessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractStageExecutor<T> implements Executor<T> {

    protected static final Logger log = LogManager.getLogger(AbstractStageExecutor.class);

    private final PipelineSessionManager pipelineSessionManager;

    public AbstractStageExecutor(PipelineSessionManager pipelineSessionManager) {
        this.pipelineSessionManager = pipelineSessionManager;
    }

    @Override
    public ActionResponse execute(Executable<T> executable) {

        try {
            return processStage(executable);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);

            final AbstractStageAction<T> stageAction = (AbstractStageAction<T>) executable;
            final Consumer<Throwable> throwableConsumer = stageAction.errorConsumer();
            Objects.requireNonNull(throwableConsumer, "Error handling consumer can't be null");

            if (stageAction.errorActionResponseReturn() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                return actionResponse.get();
            } else if (stageAction.errorActionResponseResume() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                completeStage();
                return actionResponse.get();
            } else if (stageAction.errorActionResponseTerminate() != null) {
                final Supplier<ActionResponse> actionResponse = stageAction.errorActionResponseReturn();
                terminateSession();
                return actionResponse.get();
            }

            terminateSession();
            throw new NullPointerException("No one error handling method use. Terminate pipeline.");
        }
    }

    protected abstract ActionResponse processStage(Executable<T> executable) throws Exception;

    protected void completeStage() {
        final PipelineSession pipelineSession = (PipelineSession) pipelineSessionManager.getCurrentSession();
        pipelineSession.incrementSessionStep();
    }

    protected void terminateSession() {
        // TODO add session termination here
    }

}
