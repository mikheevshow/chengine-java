package io.chengine.processor.response;

import java.util.*;

public class ResponseTypeHandlerFactory {

    private final Map<Class<?>, AbstractResponseHandler<?>> typeHandlersMap = new HashMap<>();

    public ResponseTypeHandlerFactory() {
        List<AbstractResponseHandler<?>> handlerList = List.of(
                new VoidTypeResponseHandler(),
                new SendTypeResponseHandler(),
                new EditTypeResponseHandler()
        );
        handlerList.forEach(handler -> typeHandlersMap.put(handler.supports(), handler));
    }

    public AbstractResponseHandler<?> get(Class<?> clazz) {
        return typeHandlersMap.getOrDefault(clazz, null);
    }

}
