//package io.chengine.session;
//
//import io.chengine.pipeline.Pipeline;
//import io.chengine.pipeline.StageDefinition;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class UserPipelineSessionInfo {
//
//    private final Pipeline pipeline;
//    private final List<StageDefinition> stageDefinitions;
//    private final AtomicInteger currentStep;
//
//    public UserPipelineSessionInfo(Pipeline pipeline, List<StageDefinition> stageDefinitions, int currentStep) {
//        this.pipeline = pipeline;
//        this.stageDefinitions = stageDefinitions;
//        this.currentStep = new AtomicInteger(currentStep);
//    }
//
//    public Pipeline getPipeline() {
//        return pipeline;
//    }
//
//    public List<StageDefinition> getStageDefinitions() {
//        return stageDefinitions;
//    }
//
//    public int getCurrentStep() {
//        return currentStep.get();
//    }
//
//    public void incrementStep() {
//        currentStep.incrementAndGet();
//    }
//}
