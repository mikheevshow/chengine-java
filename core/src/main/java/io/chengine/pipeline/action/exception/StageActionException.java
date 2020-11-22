package io.chengine.pipeline.action.exception;

public class StageActionException extends RuntimeException {

    public StageActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public StageActionException(Throwable cause) {
        super(cause);
    }
}
