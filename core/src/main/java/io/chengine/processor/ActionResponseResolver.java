package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseHandler;
import io.chengine.processor.response.ResponseTypeHandlerFactory;

public class ActionResponseResolver implements ResponseResolver<ActionResponse> {

    private final ResponseTypeHandlerFactory responseTypeHandlerFactory = new ResponseTypeHandlerFactory();

    @Override
    public void resolve(

            BotRequest request,
            BotResponse response,
            HandlerMethod handlerMethod,
            ActionResponse actionResponse) {

        var objClass = actionResponse.getClass();
        var handler = (AbstractActionResponseHandler) responseTypeHandlerFactory.get(objClass);
        if (handler != null) {
            handler.handle(handlerMethod, actionResponse, request, response);
        }
    }
}
