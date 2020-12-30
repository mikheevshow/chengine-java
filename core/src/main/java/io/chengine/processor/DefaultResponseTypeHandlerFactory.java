package io.chengine.processor;

import io.chengine.message.ActionResponse;

import java.util.HashMap;
import java.util.Map;

public class DefaultResponseTypeHandlerFactory implements ResponseTypeHandlerFactory {

    private final Map<Class<? extends ActionResponse>, AbstractActionResponseHandler> map = new HashMap<>();

    @Override
    public AbstractActionResponseHandler get(Class<? extends ActionResponse> actionResponseClass) {
        return map.get(actionResponseClass);
    }

    public void put(Class<? extends ActionResponse> clazz, AbstractActionResponseHandler abstractActionResponseHandler) {
        map.put(clazz, abstractActionResponseHandler);
    }
}
