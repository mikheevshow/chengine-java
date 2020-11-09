package io.chengine.pipeline;

public abstract class AbstractTrigger {
    protected ThreadLocal<PipelineSession> pipelineSessionThreadLocal;

    public abstract boolean runWith(Event event);
}
