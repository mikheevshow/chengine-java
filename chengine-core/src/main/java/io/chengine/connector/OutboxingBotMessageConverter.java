package io.chengine.connector;

public interface OutboxingBotMessageConverter<OutboxingMessage> {

	OutboxingMessage convert(BotResponse response);

}
