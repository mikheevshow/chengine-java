package io.chengine.processor;

import io.chengine.message.ActionResponse;

public interface ResponseTypeHandlerFactory {

    AbstractActionResponseHandler get(Class<? extends ActionResponse> actionResponseClass);

}
