package io.chengine.connector;

public interface BotResponseContext {

    boolean contains(Class<?> clazz);

    Object get(Class<?> clazz);

    Class<?> responseClass();

    Object getResponseObject();

}
