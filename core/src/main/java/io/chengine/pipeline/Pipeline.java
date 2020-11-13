package io.chengine.pipeline;

import java.util.List;

public class Pipeline {
    private String name;
    private Class<?> clazz;
    private List<StageDefinition> stageDefinitions;
    private int steps;
    private Class<? extends AbstractTrigger> abstractTrigger;

    public Pipeline(String name, Class<?> clazz, List<StageDefinition> stageDefinitions, int steps, Class<? extends AbstractTrigger> abstractTrigger) {
        this.name = name;
        this.clazz = clazz;
        this.stageDefinitions = stageDefinitions;
        this.steps = steps;
        this.abstractTrigger = abstractTrigger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<StageDefinition> getStages() {
        return stageDefinitions;
    }

    public void setStages(List<StageDefinition> stageDefinitions) {
        this.stageDefinitions = stageDefinitions;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Class<? extends AbstractTrigger> getTrigger() {
        return abstractTrigger;
    }

    public void setTrigger(Class<? extends AbstractTrigger> abstractTrigger) {
        this.abstractTrigger = abstractTrigger;
    }
}
