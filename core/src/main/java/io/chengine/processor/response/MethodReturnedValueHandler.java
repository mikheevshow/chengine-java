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

    /**
     * Handles returned object after handler method execution
     *
     * @param handlerMethod - handle method which was invoked firstly
     * @param returnedObject - returned object from previous {@link MethodReturnedValueHandler}
     * @param request - bot request context
     * @param response - bot response context
     */
    void handle(HandlerMethod handlerMethod, T returnedObject, BotRequestContext request, BotResponseContext response);

}
