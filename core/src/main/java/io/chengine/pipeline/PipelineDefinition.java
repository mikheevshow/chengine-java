package io.chengine.pipeline;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PipelineDefinition {

    private final String name;
    private final Class<?> clazz;
    private final int inactionTimeout;
    private final TimeUnit inactionTimeUnit;
    private final List<StageDefinition> stageDefinitions;

    public PipelineDefinition(
            String name,
            Class<?> clazz,
            int inactionTimeout,
            TimeUnit inactionTimeUnit,
            List<StageDefinition> stageDefinitions) {

        this.name = name;
        this.clazz = clazz;
        this.inactionTimeout = inactionTimeout;
        this.inactionTimeUnit = inactionTimeUnit;
        this.stageDefinitions = stageDefinitions;
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

    @Override
    public String toString() {
        return "PipelineDefinition{" +
                "name='" + name + '\'' +
                ", clazz=" + clazz +
                ", inactionTimeout=" + inactionTimeout +
                ", inactionTimeUnit=" + inactionTimeUnit +
                ", stageDefinitions=" + stageDefinitions +
                '}';
    }
}
