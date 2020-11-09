package io.chengine.annotation.dto;

import java.util.List;

public class Pipeline {
    private String name;
    private Class<?> clazz;
    private List<Stage> stages;
    private int steps;
    private Class<? extends AbstractTrigger> abstractTrigger;

    public Pipeline(String name, Class<?> clazz, List<Stage> stages, int steps, Class<? extends AbstractTrigger> abstractTrigger) {
        this.name = name;
        this.clazz = clazz;
        this.stages = stages;
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

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
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
