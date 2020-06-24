package io.chengine;

public interface MessageProcessor<T> {

	void onMessageReceived(T message);

	default boolean isCommand(T message) {
		throw new UnsupportedOperationException();
	}
}
