package io.chengine.pipeline.action;

public class StageCheck<T> {

    private final boolean success;
    private final T data;

    private StageCheck(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> StageCheck<T> success() {
        return new StageCheck<>(true, null);
    }

    public static <T> StageCheck<T> success(T data) {
        return new StageCheck<>(true, data);
    }

    public static <T> StageCheck<T> fail() {
        return new StageCheck<>(false, null);
    }

    public static <T> StageCheck<T> fail(T data) {
        return new StageCheck<>(false, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public T data() {
        return data;
    }
}
