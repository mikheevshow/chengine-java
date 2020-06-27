package io.chengine.connector;

public interface BotResponseConverter<T> {

	T convert(BotResponse response);

}
