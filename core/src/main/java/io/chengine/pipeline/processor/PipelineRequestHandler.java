package io.chengine.pipeline.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;

public interface PipelineRequestHandler {

    void handleRequest(BotRequest request, BotResponse response);

}
