package io.chengine.processor;

import io.chengine.message.ActionResponse;

public interface ResponseTypeHandlerFactory {

    AbstractActionResponseMethodReturnedValueHandler get(Class<? extends ActionResponse> actionResponseClass);

}
