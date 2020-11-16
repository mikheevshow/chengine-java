package io.chengine.session.pipeline;

import io.chengine.connector.Chat;
import io.chengine.connector.User;
import io.chengine.pipeline.Pipeline;
import io.chengine.pipeline.StageDefinition;
import io.chengine.session.Session;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSession implements Session {

    private final UUID uuid;
    private final Pipeline pipeline;
    private final User user;
    private final Chat chat;

    private final int ttl;
    private final TimeUnit ttlTimeUnit;

    private volatile int currentStep;
    private volatile boolean terminated;

    public PipelineSession(
            UUID uuid,
            Pipeline pipeline,
            User user,
            Chat chat,
            int ttl,
            TimeUnit ttlTimeUnit,
            int currentStep,
            boolean terminated) {

        this.uuid = uuid;
        this.pipeline = pipeline;
        this.user = user;
        this.chat = chat;
        this.ttl = ttl;
        this.ttlTimeUnit = ttlTimeUnit;
        this.currentStep = currentStep;
        this.terminated = terminated;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public Pipeline pipeline() {
        return pipeline;
    }

    @Override
    public StageDefinition currentStage() {
        return this.pipeline().getStageDefinitions().get(currentStep);
    }

    @Override
    public User user() {
        return user;
    }

    @Override
    public Chat chat() {
        return this.chat;
    }

    @Override
    public int getCurrentStep() {
        return currentStep;
    }

    @Override
    public int getTtl() {
        return ttl;
    }

    @Override
    public TimeUnit getTtlTimeUnit() {
        return ttlTimeUnit;
    }

    @Override
    public boolean isTerminated() {
        return terminated;
    }

    public boolean incrementSessionStep() {
        synchronized (this) {
            currentStep++;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PipelineSession that = (PipelineSession) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
