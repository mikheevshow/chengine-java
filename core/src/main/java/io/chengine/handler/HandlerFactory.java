package io.chengine.handler;

import io.chengine.command.Command;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HandlerFactory {

    private Map<Command, HandlerMethod> commandHandlerMethodMap = new HashMap<>();

    private Map<String, HandlerMethod> stringCommandHandlerMap = new HashMap<>();

    private Map<Class<? extends Annotation>, HandlerMethod> singleHandlerMethodMap = new HashMap<>();

    public void put(Command command, HandlerMethod handlerMethod) {
        
    }

    public void put(Class<? extends  Annotation> annotation, HandlerMethod handlerMethod) {
        Objects.requireNonNull(annotation);
        Objects.requireNonNull(handlerMethod);
        if (singleHandlerMethodMap.containsKey(annotation)) {
            throw new RuntimeException("Handler annotation " + annotation.getName() + " already used.");
        }

        singleHandlerMethodMap.put(annotation, handlerMethod);
    }

}
