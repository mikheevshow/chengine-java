package io.chengine.processor;

import io.chengine.message.ActionResponse;
import io.chengine.processor.response.AbstractActionResponseHandler;

public interface ResponseTypeHandlerFactory {

    AbstractActionResponseHandler get(Class<? extends ActionResponse> actionResponseClass);

}
