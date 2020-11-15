package io.chengine.pipeline;

import io.chengine.connector.BotRequest;

import java.util.UUID;

public interface PipelineSessionManager {

    /**
     * Gets session based on user, chat and bot api identifier
     *
     * @param request - bot request
     * @return {@link PipelineSession} object
     */
    PipelineSession getSession(BotRequest request);

    boolean invalidateSession(UUID uuid);

}
