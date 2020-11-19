package io.chengine;

public class FailTestException extends RuntimeException {

    public FailTestException() {
    }

    public FailTestException(String message) {
        super(message);
    }
}
