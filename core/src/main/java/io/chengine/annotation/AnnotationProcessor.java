package io.chengine.annotation;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface AnnotationProcessor {

    Set<Class<? extends Annotation>> supports();

    void process(Object handler);

}
