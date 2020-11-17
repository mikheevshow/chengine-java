package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.processor.MessageProcessor;

public interface RequestHandler {

    void setMessageProcessor(MessageProcessor<BotRequest, BotResponse> messageProcessor);

}
