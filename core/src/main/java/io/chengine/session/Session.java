package io.chengine.session;

import io.chengine.connector.Chat;
import io.chengine.connector.User;
import io.chengine.pipeline.Pipeline;
import io.chengine.pipeline.StageDefinition;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface Session {
    
    UUID uuid();

    Pipeline pipeline();

    StageDefinition currentStage();

    User user();

    Chat chat();

    int getCurrentStep();

    int getTtl();

    TimeUnit getTtlTimeUnit();

    boolean isTerminated();

    boolean incrementSessionStep();
}
