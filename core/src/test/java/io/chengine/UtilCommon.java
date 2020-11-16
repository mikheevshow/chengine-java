package io.chengine;

import io.chengine.connector.User;
import io.chengine.pipeline.Pipeline;
import io.chengine.session.Session;
import io.chengine.session.SessionKey;
import io.chengine.session.pipeline.PipelineSession;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UtilCommon {

    public static SessionKey createSessionKey(String api) {
        return new SessionKey(api, 1L, 1L);
    }

    public static Session createPipelineSession() {
        return new PipelineSession(
            UUID.randomUUID(),
            createEmptyPipeline(),
            createUserNotBot(),
            5,
            TimeUnit.MINUTES,
            0,
            false
        );
    }

    public static User createUserNotBot() {
        return new User(1, false,
            "Andrey", "Borisov", "andreybrsv",
            "ru", true, true, true);
    }

    public static Pipeline createEmptyPipeline() {
        return new Pipeline("pipeline", null, Collections.emptyList(), null);
    }
}
