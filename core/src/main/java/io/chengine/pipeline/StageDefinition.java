package io.chengine.pipeline;

import io.chengine.annotation.StageMode;
import io.chengine.method.Method;

public class StageDefinition {
    private String name;
    private Method method;
    private int step;
    private StageMode stageMode;

    public StageDefinition(String name, Method method, int step, StageMode stageMode) {
        this.name = name;
        this.method = method;
        this.step = step;
        this.stageMode = stageMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
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
