package io.chengine.pipeline.action;

import io.chengine.session.ChengineSessionContext;
import io.chengine.session.pipeline.PipelineSessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executors {

    private static final Logger log = LogManager.getLogger(Executors.class);

    private final PipelineSessionManager pipelineSessionManager;

    public Executors(PipelineSessionManager pipelineSessionManager) {
        this.pipelineSessionManager = pipelineSessionManager;
    }

    public <T> Executor<T> fire() {
        return new FireAndForgetExecutor<>(pipelineSessionManager);
    }

    public <T> Executor<T> check() {
        return new CheckStageExecutor<>(pipelineSessionManager);
    }

}
