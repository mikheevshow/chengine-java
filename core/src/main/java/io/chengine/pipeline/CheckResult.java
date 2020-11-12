package io.chengine.pipeline;

public class CheckResult<T> {

    private final boolean success;
    private final T data;

    private CheckResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> CheckResult<T> success() {
        return new CheckResult<>(true, null);
    }

    public static <T> CheckResult<T> success(T data) {
        return new CheckResult<>(true, data);
    }

    public static <T> CheckResult<T> fail() {
        return new CheckResult<>(false, null);
    }

    public static <T> CheckResult<T> fail(T data) {
        return new CheckResult<>(false, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}
