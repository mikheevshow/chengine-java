package io.chengine.connector;

public interface IncomingBotMessageConverter<IncomingMessage> {

	BotRequest convert(IncomingMessage message);

}
