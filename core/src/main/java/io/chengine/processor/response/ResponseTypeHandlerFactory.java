package io.chengine.processor.response;

import java.util.*;

public class ResponseTypeHandlerFactory {

    private final Map<Class<?>, AbstractResponseTypeHandler> typeHandlersMap = new HashMap<>();

    public ResponseTypeHandlerFactory() {
        List<AbstractResponseTypeHandler> handlerList = List.of(
                new VoidTypeResponseHandler(),
                new SendTypeResponseHandler(),
                new EditTypeResponseHandler()
        );
        handlerList.forEach(handler -> typeHandlersMap.put(handler.supports(), handler));
    }

    public AbstractResponseTypeHandler get(Class<?> clazz) {
        return typeHandlersMap.getOrDefault(clazz, null);
    }

}
