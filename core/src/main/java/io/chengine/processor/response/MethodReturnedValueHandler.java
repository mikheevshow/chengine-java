package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.HandlerMethod;

public interface MethodReturnedValueHandler<T> {

    void handle(HandlerMethod handlerMethod, T returnedObject, BotRequest request, BotResponse response);

}
