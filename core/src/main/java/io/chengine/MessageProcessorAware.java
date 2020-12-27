package io.chengine;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.processor.MessageProcessor;

public interface MessageProcessorAware {

    void setMessageProcessor(MessageProcessor<BotRequestContext, BotResponseContext> messageProcessor);

}
