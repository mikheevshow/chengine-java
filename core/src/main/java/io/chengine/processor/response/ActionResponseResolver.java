package io.chengine.processor.response;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.ResponseResolver;

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
        final MethodReturnedValueHandler<ActionResponse> handler = (MethodReturnedValueHandler<ActionResponse>) responseTypeHandlerFactory.get(objClass);
        if (handler != null) {
            handler.handle(handlerMethod, actionResponse, request, response);
        }
    }
}
