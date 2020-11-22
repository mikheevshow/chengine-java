package io.chengine.processor.response;

import java.util.*;

public class ResponseTypeHandlerFactory {

    private final Map<Class<?>, AbstractMethodReturnedValueHandler<?>> typeHandlersMap = new HashMap<>();

    public ResponseTypeHandlerFactory() {
        List<AbstractMethodReturnedValueHandler<?>> handlerList = List.of(
                new VoidTypeResponseHandler(),
                new SendTypeResponseHandler(),
                new EditTypeResponseHandler()
        );
        handlerList.forEach(handler -> typeHandlersMap.put(handler.supports(), handler));
    }

    public AbstractMethodReturnedValueHandler<?> get(Class<?> clazz) {
        return typeHandlersMap.getOrDefault(clazz, null);
    }

}
