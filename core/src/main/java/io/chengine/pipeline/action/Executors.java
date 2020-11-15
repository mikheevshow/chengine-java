package io.chengine.pipeline.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executors {

    private static final Logger log = LogManager.getLogger(Executors.class);

    private Executors() {}

    static <T> Executor<T> fire() {
        return new FireAndForgetExecutor<>();
    }

    static <T> Executor<T> check() {
        return new CheckStageExecutor<>();
    }

}
