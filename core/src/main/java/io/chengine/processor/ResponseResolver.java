package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.Method;

public interface ResponseResolver {

    void resolve(BotRequest botRequest, BotResponse botResponse, Method method, Object object);

}
