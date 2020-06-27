package io.chengine.handler;

public class HandlerNotFoundException extends RuntimeException {

	public HandlerNotFoundException() {
	}

	public HandlerNotFoundException(String message) {
		super(message);
	}

	public HandlerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
