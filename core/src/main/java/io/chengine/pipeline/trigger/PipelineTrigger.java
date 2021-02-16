package io.chengine.pipeline.trigger;

import io.chengine.message.ActionResponse;

public class PipelineTrigger implements ActionResponse {

    private final String pipelineName;

    private PipelineTrigger(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public static PipelineTrigger byClass(Class<?> pipelineClass) {
        if (pipelineClass == null) {
            throw new IllegalArgumentException("Pipeline class can't be null");
        }

        return new PipelineTrigger(pipelineClass.getName());
    }

    public static PipelineTrigger byName(String pipelineName) {
        if (pipelineName == null) {
            throw new IllegalArgumentException("Pipeline name can't be null");
        }

        return new PipelineTrigger(pipelineName);
    }

    public String getPipelineName() {
        return pipelineName;
    }
}
