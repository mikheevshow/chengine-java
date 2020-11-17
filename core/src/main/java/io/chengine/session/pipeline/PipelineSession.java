package io.chengine.session.pipeline;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.Chat;
import io.chengine.connector.User;
import io.chengine.pipeline.StageDefinition;
import io.chengine.session.Session;
import io.chengine.session.UserPipelineSessionInfo;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PipelineSession implements Session<UserPipelineSessionInfo> {

    private final UUID uuid;
    private final User user;
    private final Chat chat;

    private final UserPipelineSessionInfo pipelineSessionInfo;

    private final int ttl;
    private final TimeUnit ttlTimeUnit;

    private volatile boolean terminated;

    public PipelineSession(
            UUID uuid,
            UserPipelineSessionInfo pipelineSessionInfo,
            User user,
            Chat chat,
            int ttl,
            TimeUnit ttlTimeUnit,
            int currentStep,
            boolean terminated) {

        this.uuid = uuid;
        this.pipelineSessionInfo = pipelineSessionInfo;
        this.user = user;
        this.chat = chat;
        this.ttl = ttl;
        this.ttlTimeUnit = ttlTimeUnit;
        this.terminated = terminated;

    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public BotApiIdentifier api() {
        return null;
    }

    public StageDefinition currentStage() {
        return this.pipelineSessionInfo
                .getStageDefinitions()
                .get(getCurrentStep());
    }

    @Override
    public User user() {
        return user;
    }

    @Override
    public Chat chat() {
        return this.chat;
    }

    public int getCurrentStep() {
        return pipelineSessionInfo.getCurrentStep();
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
        pipelineSessionInfo.incrementStep();
        return true;
    }

    @Override
    public UserPipelineSessionInfo data() {
        return this.pipelineSessionInfo;
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
