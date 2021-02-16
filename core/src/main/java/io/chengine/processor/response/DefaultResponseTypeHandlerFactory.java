package io.chengine.processor.response;

import io.chengine.message.ActionResponse;

import java.util.HashMap;
import java.util.Map;

public class DefaultResponseTypeHandlerFactory implements ResponseTypeHandlerFactory {

    private final Map<Class<? extends ActionResponse>, MethodReturnedValueHandler<? extends ActionResponse>> map = new HashMap<>();

    @Override
    public MethodReturnedValueHandler<? extends ActionResponse> get(Class<? extends ActionResponse> actionResponseClass) {
        return map.get(actionResponseClass);
    }

    public void put(Class<? extends ActionResponse> clazz, MethodReturnedValueHandler<? extends ActionResponse> abstractActionResponseHandler) {
        map.put(clazz, abstractActionResponseHandler);
    }
}
