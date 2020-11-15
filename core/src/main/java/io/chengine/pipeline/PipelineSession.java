package io.chengine.pipeline;

import io.chengine.connector.User;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSession {

    private UUID uuid;
    private Pipeline pipeline;
    private User user;
    private int currentStep;
    private int ttl;
    private TimeUnit ttlTimeUnit;

    private volatile boolean terminated;

    public UUID getSessionUuid() {
        return uuid;
    }

    public Pipeline getPipeline() {
        return pipeline;
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
}
