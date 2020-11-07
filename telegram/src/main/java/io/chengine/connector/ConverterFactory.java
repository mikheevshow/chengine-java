package io.chengine.connector;

public interface ConverterFactory {

    <T> BotResponseConverter<T> get(Class<T> tClass);

}
