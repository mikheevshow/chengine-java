package io.chengine.connector;

public interface BotRequest {

	BotApiIdentifier identifier();

	Message<?> message();

	User<?> user();

	Chat<?> chat();

}
