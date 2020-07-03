package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;

public interface ResponseResolver {

    void resolve(BotRequest botRequest, BotResponse botResponse, Object object);

}
