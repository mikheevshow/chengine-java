package io.chengine.connector;

public interface BotRequest {

	BotApiIdentifier identifier();

	boolean isCallback();

	Message<?> message();

	User<?> user();

	Chat<?> chat();

}
