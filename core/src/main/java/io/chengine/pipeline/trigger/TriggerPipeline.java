package io.chengine.pipeline.trigger;

import io.chengine.message.ActionResponse;

public class TriggerPipeline implements ActionResponse {

    private final String pipelineName;

    private TriggerPipeline(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public static TriggerPipeline with(Class<?> pipelineClass) {
        if (pipelineClass == null) {
            throw new IllegalArgumentException("Pipeline class can't be null");
        }

        return new TriggerPipeline(pipelineClass.getName());
    }

    public static TriggerPipeline with(String pipelineName) {
        if (pipelineName == null) {
            throw new IllegalArgumentException("Pipeline name can't be null");
        }

        return new TriggerPipeline(pipelineName);
    }

    public String getPipelineName() {
        return pipelineName;
    }
}
