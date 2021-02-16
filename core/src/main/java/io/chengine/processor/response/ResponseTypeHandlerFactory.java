package io.chengine.processor.response;

import io.chengine.message.ActionResponse;

public interface ResponseTypeHandlerFactory {

    MethodReturnedValueHandler<? extends ActionResponse> get(Class<? extends ActionResponse> actionResponseClass);

}
