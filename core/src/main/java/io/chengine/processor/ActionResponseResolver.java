package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;

public class ActionResponseResolver implements ResponseResolver<ActionResponse> {

    private final ResponseTypeHandlerFactory responseTypeHandlerFactory;

    public ActionResponseResolver(ResponseTypeHandlerFactory responseTypeHandlerFactory) {
        this.responseTypeHandlerFactory = responseTypeHandlerFactory;
    }

    @Override
    public void resolve(
            final BotRequestContext request,
            final BotResponseContext response,
            final HandlerMethod handlerMethod,
            final ActionResponse actionResponse) {

        final Class<? extends ActionResponse> objClass = actionResponse.getClass();
        final AbstractActionResponseMethodReturnedValueHandler handler = responseTypeHandlerFactory.get(objClass);
        if (handler != null) {
            handler.handle(handlerMethod, actionResponse, request, response);
        }
    }
}
