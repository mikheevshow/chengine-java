package io.chengine.connector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultBotResponseContext implements BotResponseContext {

    private final Map<Class<?>, Object> typeObjectMap = new LinkedHashMap<>();
    private Object responseObject;

    @Override
    public boolean contains(Class<?> clazz) {
        return typeObjectMap.containsKey(clazz);
    }

    @Override
    public Object get(Class<?> clazz) {
        return typeObjectMap.get(clazz);
    }

    @Override
    public Object getResponseObject() {
        return responseObject;
    }

    @Override
    public Class<?> responseClass() {
        return responseObject.getClass();
    }

    public void put(Class<?> clazz, Object o) {
        typeObjectMap.put(clazz, o);
    }

    public void setResponseObject(Object o) {
        this.responseObject = o;
    }
}
