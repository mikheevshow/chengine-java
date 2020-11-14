package io.chengine.processor.response;

import java.util.*;

public class ResponseTypeHandlerFactory {

    private final Map<Class<?>, AbstractActionResponseHandler> typeHandlersMap = new HashMap<>();

    public ResponseTypeHandlerFactory() {
        List<AbstractActionResponseHandler> handlerList = List.of(
                new VoidTypeResponseHandler(),
                new SendTypeResponseHandler(),
                new EditTypeResponseHandler()
        );
        handlerList.forEach(handler -> typeHandlersMap.put(handler.supports(), handler));
    }

    public AbstractActionResponseHandler get(Class<?> clazz) {
        return typeHandlersMap.getOrDefault(clazz, null);
    }

}
