package io.chengine.session.pipeline;

import io.chengine.connector.User;
import io.chengine.pipeline.Pipeline;
import io.chengine.pipeline.StageDefinition;
import io.chengine.session.Session;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSession implements Session {

    private UUID uuid;
    private Pipeline pipeline;
    private User user;

    private int ttl;
    private TimeUnit ttlTimeUnit;

    private volatile int currentStep;
    private volatile boolean terminated;

    public PipelineSession(UUID uuid, Pipeline pipeline, User user, int ttl, TimeUnit ttlTimeUnit, int currentStep, boolean terminated) {
        this.uuid = uuid;
        this.pipeline = pipeline;
        this.user = user;
        this.ttl = ttl;
        this.ttlTimeUnit = ttlTimeUnit;
        this.currentStep = currentStep;
        this.terminated = terminated;
    }

    public UUID getSessionUuid() {
        return uuid;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public StageDefinition currentStage() {
        return this.getPipeline().getStageDefinitions().get(currentStep);
    }

    public User getUser() {
        return user;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int getTtl() {
        return ttl;
    }

    public TimeUnit getTtlTimeUnit() {
        return ttlTimeUnit;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public boolean incrementSessionStep() {
        synchronized (this) {
            currentStep++;
        }

        return true;
    }
}
