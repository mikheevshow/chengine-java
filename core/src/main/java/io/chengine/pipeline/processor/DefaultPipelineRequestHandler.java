package io.chengine.pipeline.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.pipeline.PipelineSession;
import io.chengine.pipeline.PipelineSessionManager;

public class DefaultPipelineRequestHandler implements PipelineRequestHandler {

    private PipelineSessionManager pipelineSessionManager;

    @Override
    public void handleRequest(BotRequest request, BotResponse response) {
        final PipelineSession session = pipelineSessionManager.getSession(request);
        // TODO define pipeline logic
    }

}
