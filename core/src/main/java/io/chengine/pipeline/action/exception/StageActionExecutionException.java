package io.chengine.pipeline.action.exception;

public class StageActionExecutionException extends RuntimeException {

    public StageActionExecutionException(String message) {
        super(message);
    }

    public StageActionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
