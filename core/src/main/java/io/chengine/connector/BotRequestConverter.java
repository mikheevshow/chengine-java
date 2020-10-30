package io.chengine.connector;

public interface BotRequestConverter<T> {

    BotRequest convert(T request) throws Exception;

}
