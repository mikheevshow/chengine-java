package io.chengine.naturelang;

import io.chengine.command.Command;
import io.chengine.handler.HandlerMethod;
import io.chengine.handler.HandlerVisitor;
import io.chengine.method.HandlerMethodRegistry;

import java.lang.reflect.Method;

public class PlainTextMapAnnotationProcessor implements HandlerVisitor {

    private static final String EMPTY_STRING = "";

    private final TextCommandMapper commandMapper;
    private final HandlerMethodRegistry handlerMethodRegistry;

    public PlainTextMapAnnotationProcessor(
            TextCommandMapper commandMapper,
            HandlerMethodRegistry handlerMethodRegistry) {

        this.commandMapper = commandMapper;
        this.handlerMethodRegistry = handlerMethodRegistry;
    }

    @Override
    public void visitHandler(HandlerMethod handler) {
        final Method method = handler.getMethod();
        if (method.isAnnotationPresent(PlainTextMap.class)) {
            final PlainTextMap annotation = method.getAnnotation(PlainTextMap.class);
            validate(annotation, method);

            final String prefix = annotation.prefix();
            final boolean ignoreCase = annotation.ignoreCase();
            if (annotation.alternateValues().length != 0) {
                for (final String text : annotation.alternateValues()) {
                    mapText(text, ignoreCase, method);
                }
            } else {
                final String text = annotation.value();
                mapText(text, ignoreCase, method);
            }
        }
    }

    private void validate(PlainTextMap annotation, Method method) {
        if (!EMPTY_STRING.equals(annotation.value()) && annotation.alternateValues().length != 0) {
            throw new RuntimeException("Value and alternate values are not empty at the same time. Please use just one of them.");
        } else if (annotation.alternateValues().length == 0 && EMPTY_STRING.equals(annotation.value())) {
            throw new RuntimeException("Value and alternate values can't be empty at the same time. Please use just one of them.");
        }
    }

    private void mapText(String text, boolean ignoreCase, Method method) {
        final Command command = handlerMethodRegistry.getByReflectMethod(method);
        // TODO think about ignore case processing
        commandMapper.mapAll(command, text);
    }
}

