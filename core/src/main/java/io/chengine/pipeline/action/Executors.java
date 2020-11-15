package io.chengine.pipeline.action;

import io.chengine.connector.BotRequest;
import io.chengine.pipeline.PipelineSession;
import io.chengine.pipeline.PipelineSessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Executors {

    private static final Logger log = LogManager.getLogger(Executors.class);

    private Executors() {}

    static <T> Executor<T> fire() {
        return new FireAndForgetExecutor<>(pipelineSessionManager);
    }

    static <T> Executor<T> check() {
        return new CheckStageExecutor<>(pipelineSessionManager);
    }

}
