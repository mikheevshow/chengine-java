package io.chengine.processor.response;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;

import javax.annotation.Nullable;

public interface MethodReturnedValueHandler<T> {

    /**
     * Sets next handler in chain of handlers.
     *
     * @param nextHandler - next handler in chain.
     */
    void setNext(@Nullable MethodReturnedValueHandler<?> nextHandler);

    void handle(HandlerMethod handlerMethod, T returnedObject, BotRequestContext request, BotResponseContext response);

}
