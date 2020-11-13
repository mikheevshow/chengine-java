package io.chengine.pipeline.exec;

public abstract class Executors {

    private static final Executor fireAndForgetStageExecutor = new FireAndForgetExecutor();
    private static final Executor checkStageExecutor = new CheckStageExecutor();

    private Executors() {}

    public static Executor fire() {
        return fireAndForgetStageExecutor;
    }

    public static Executor check() {
        return checkStageExecutor;
    }

}
