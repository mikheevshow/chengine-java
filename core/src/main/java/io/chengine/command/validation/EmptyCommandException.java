package io.chengine.command.validation;

public class EmptyCommandException extends Exception {

	public EmptyCommandException() {
	}

	public EmptyCommandException(String message) {
		super(message);
	}
}
