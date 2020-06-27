package io.chengine.connector;

public interface BotRequest {

	BotClientIdentifier identifier();

	Message message();

	User<?> user();

	Chat<?> chat();

}
