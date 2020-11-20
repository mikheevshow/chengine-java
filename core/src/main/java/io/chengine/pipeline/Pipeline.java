package io.chengine.pipeline;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pipeline {

    private final String name;
    private final Class<?> clazz;
    private final int inactionTimeout;
    private final TimeUnit inactionTimeUnit;
    private final List<StageDefinition> stageDefinitions;
    private final Class<? extends EventTrigger> trigger;

    public Pipeline(
            String name,
            Class<?> clazz,
            List<StageDefinition> stageDefinitions,
            Class<? extends EventTrigger> abstractTrigger) {

        this(name, clazz, 0, TimeUnit.SECONDS, stageDefinitions, abstractTrigger);
    }

    // Приватный конструктор должен содеражать все поля
    // Все остальные конструкторы должны выставлять начальные
    // значения, вызывая его
    private Pipeline(
            String name, Class<?> clazz,
            int inactionTimeout,
            TimeUnit inactionTimeUnit,
            List<StageDefinition> stageDefinitions,
            Class<? extends EventTrigger> trigger) {

        this.name = name;
        this.clazz = clazz;
        this.inactionTimeout = inactionTimeout;
        this.inactionTimeUnit = inactionTimeUnit;
        this.stageDefinitions = stageDefinitions;
        this.trigger = trigger;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public int getInactionTimeout() {
        return inactionTimeout;
    }

    public TimeUnit getInactionTimeUnit() {
        return inactionTimeUnit;
    }

    public List<StageDefinition> getStageDefinitions() {
        return stageDefinitions;
    }

    public int getSteps() {
        return stageDefinitions.size();
    }

    public Class<? extends EventTrigger> getTrigger() {
        return trigger;
    }
}
