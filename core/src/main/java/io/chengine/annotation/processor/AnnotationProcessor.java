package io.chengine.annotation.processor;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface AnnotationProcessor<T, G> {

    Collection<Class<? extends Annotation>> supports();

    T process(G input);

}
