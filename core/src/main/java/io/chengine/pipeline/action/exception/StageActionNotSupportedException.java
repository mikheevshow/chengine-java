package io.chengine.pipeline.action.exception;

public class StageActionNotSupportedException extends RuntimeException {

    public StageActionNotSupportedException(String message) {
        super(message);
    }
}
