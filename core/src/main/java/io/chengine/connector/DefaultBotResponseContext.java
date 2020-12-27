package io.chengine.connector;

import java.util.HashMap;
import java.util.Map;

public class DefaultBotResponseContext implements BotResponseContext {

    private final Map<Class<?>, Object> typeObjectMap = new HashMap<>();

    @Override
    public boolean contains(Class<?> clazz) {
        return typeObjectMap.containsKey(clazz);
    }

    @Override
    public Object get(Class<?> clazz) {
        return typeObjectMap.get(clazz);
    }

    public void put(Class<?> clazz, Object o) {
        typeObjectMap.put(clazz, o);
    }
}
