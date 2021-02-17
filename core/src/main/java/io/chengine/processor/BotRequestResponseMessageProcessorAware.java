package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;

public interface BotRequestResponseMessageProcessorAware extends MessageProcessorAware<BotRequestContext, BotResponseContext>{}
