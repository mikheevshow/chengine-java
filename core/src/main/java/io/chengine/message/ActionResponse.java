package io.chengine.message;

import java.io.Serializable;
import java.util.function.Supplier;

public interface ActionResponse extends Serializable {

    default <T> T validateSupplier(Supplier<T> supplier) {
        if (supplier == null) {
            throw new NullPointerException("Supplier function is null, but required non null");
        }
        final T value = supplier.get();
        if (value == null) {
            throw new NullPointerException("Supplier function return value is null, but required non null");
        }
        return value;
    }

}
