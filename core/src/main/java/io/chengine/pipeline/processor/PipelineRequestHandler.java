package io.chengine.pipeline.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;

public interface PipelineRequestHandler {

    void handleRequest(BotRequestContext request, BotResponseContext response);

}
