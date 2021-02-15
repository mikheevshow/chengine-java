package io.chengine.session;

import io.chengine.pipeline.PipelineDefinition;
import io.chengine.pipeline.StageDefinition;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PipelineSessionInfo {

    private final PipelineDefinition pipelineDefinition;
    private final List<StageDefinition> stageDefinitions;
    private final AtomicInteger currentStep;

    public PipelineSessionInfo(PipelineDefinition pipelineDefinition, List<StageDefinition> stageDefinitions, int currentStep) {
        this.pipelineDefinition = pipelineDefinition;
        this.stageDefinitions = stageDefinitions;
        this.currentStep = new AtomicInteger(currentStep);
    }

    public PipelineDefinition getPipeline() {
        return pipelineDefinition;
    }

    public List<StageDefinition> getStageDefinitions() {
        return stageDefinitions;
    }

    public int getCurrentStep() {
        return currentStep.get();
    }

    public void incrementStep() {
        currentStep.incrementAndGet();
    }
}
