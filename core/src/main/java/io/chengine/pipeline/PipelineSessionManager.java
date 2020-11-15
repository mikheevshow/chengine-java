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

    PipelineSession getSession(UUID uuid);

    PipelineSession getCurrentSession();

    boolean invalidateSession(UUID uuid);

    boolean invalidateCurrentSession();

}
