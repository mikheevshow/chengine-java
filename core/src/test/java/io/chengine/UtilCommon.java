package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.Chat;
import io.chengine.connector.User;
import io.chengine.pipeline.Pipeline;
import io.chengine.session.Session;
import io.chengine.session.SessionKey;
import io.chengine.session.UserPipelineSessionInfo;
import io.chengine.session.pipeline.PipelineSession;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UtilCommon {

    public static SessionKey createSessionKey(String api) {
        return new SessionKey(api, 1L, 1L);
    }

    public static Session<UserPipelineSessionInfo> createPipelineSession() {
        return new PipelineSession(
            UUID.randomUUID(),
            createEmptyPipeline(),
            createUserNotBot(),
            createChat(),
                () -> "telegram",
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

    public static Chat createChat() {
        return new Chat(
                1L,
                "group",
                "Halo Welt",
                "title",
                "firstName",
                "lastName",
                "description",
                "http://www.invite.com"
        );
    }

    public static UserPipelineSessionInfo createEmptyPipeline() {
        var pipeline = new Pipeline("pipeline", null, Collections.emptyList(), null);
        return new UserPipelineSessionInfo(pipeline, Collections.emptyList(), 0);
    }

    public static BotRequest createBotRequest() {
        return new BotRequest(
                null,
                () -> "telegram",
                false,
                false,
                false,
                false,
                createChat(),
                createUserNotBot(),
                null,
                null);
    }
}