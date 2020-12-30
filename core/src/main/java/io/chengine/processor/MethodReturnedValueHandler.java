package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;

public interface MethodReturnedValueHandler<T> {

    void handle(HandlerMethod handlerMethod, T returnedObject, BotRequestContext request, BotResponseContext response);

}
