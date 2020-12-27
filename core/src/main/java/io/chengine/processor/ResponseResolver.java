package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;

public interface ResponseResolver<T> {

    void resolve(BotRequestContext botRequest, BotResponseContext botResponseContext, HandlerMethod handlerMethod, T object);

}
