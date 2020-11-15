package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.HandlerMethod;

public interface ResponseResolver<T> {

    void resolve(BotRequest botRequest, BotResponse botResponse, HandlerMethod handlerMethod, T object);

}
