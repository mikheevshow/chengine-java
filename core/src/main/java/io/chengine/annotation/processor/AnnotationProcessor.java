package io.chengine.annotation.processor;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Consumer;

public interface AnnotationProcessor<T, G> {

    Collection<Class<? extends Annotation>> supports();

    void process(T input, Consumer<G> processingCallback) throws Exception;

    default void process(T input) throws Exception {
        process(input, callback -> {});
    }

}
