package io.chengine.annotation.processor;

import io.chengine.annotation.HandleCommand;
import io.chengine.method.Method;

import java.lang.annotation.Annotation;
import java.util.*;

public class HandleCommandAnnotationProcessor implements AnnotationProcessor<Map<String, Method>, Collection<?>> {

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(HandleCommand.class);
    }

    @Override
    public Map<String, Method> process(Collection<?> handlers) {
//        var map = new HashMap<String, Method>();
//        handlers
//                .parallelStream()
//                .forEach(handler -> {
//                    Arrays
//                            .stream(handler.getClass().getDeclaredMethods())
//
//                });
        return null;
    }
}
