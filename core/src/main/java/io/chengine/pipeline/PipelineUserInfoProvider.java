package io.chengine.pipeline;

import io.chengine.connector.BotRequest;

public interface PipelineUserInfoProvider {

    boolean isUserInPipeline(BotRequest request);

}
