package io.chengine.pipeline;

import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.annotation.StageMode;

public class StageDefinition {

    private String name;
    private HandlerMethod handlerMethod;
    private int step;
    private StageMode stageMode;

    public StageDefinition(String name, HandlerMethod handlerMethod, int step, StageMode stageMode) {
        this.name = name;
        this.handlerMethod = handlerMethod;
        this.step = step;
        this.stageMode = stageMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HandlerMethod getMethod() {
        return handlerMethod;
    }

    public void setMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public StageMode getStageMode() {
        return stageMode;
    }

    public void setStageMode(StageMode stageMode) {
        this.stageMode = stageMode;
    }
}
