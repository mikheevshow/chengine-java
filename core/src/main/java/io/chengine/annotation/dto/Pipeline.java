package io.chengine.annotation.dto;

import java.util.List;

public class Pipeline {
    private String name;
    private Class<?> clazz;
    private List<Stage> stages;
    private int steps;
    private Trigger trigger;

    public Pipeline(String name, Class<?> clazz, List<Stage> stages, int steps, Trigger trigger) {
        this.name = name;
        this.clazz = clazz;
        this.stages = stages;
        this.steps = steps;
        this.trigger = trigger;
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

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
