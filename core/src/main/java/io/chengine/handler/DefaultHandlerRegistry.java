package io.chengine.handler;

import io.chengine.command.Command;
import io.chengine.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultHandlerRegistry implements HandlerRegistry {

    private final Map<String, HandlerMethod> commandHandlerMap = new HashMap<>();
    private final Map<Class<? extends Annotation>, HandlerMethod> singleHandlerMap = new HashMap<>();

    public DefaultHandlerRegistry() {}

    @Override
    public Set<String> getAllPaths() {
        return commandHandlerMap.keySet();
    }

    @Override
    public Set<? extends HandlerMethod> getAllHandlers() {
        Set<HandlerMethod> handlers = new HashSet<>();
        handlers.addAll(commandHandlerMap.values());
        handlers.addAll(singleHandlerMap.values());
        return handlers;
    }

    @Override
    public HandlerMethod getHandlerByCommand(String command) {
        if (command == null) {
            throw new NullPointerException("Command can't be null");
        }
        final HandlerMethod handlerMethod = commandHandlerMap.get(command);
        if (handlerMethod == null) {
            throw new RuntimeException("Handler not found for command: " + command);
        }

        return handlerMethod;
    }

    public void putCommand(String command, HandlerMethod handlerMethod) {
        if (commandHandlerMap.containsKey(command)) {
            throw new RuntimeException("Command: " + command + " already registered");
        }

        commandHandlerMap.put(command, handlerMethod);
    }

    public void putCommand(Command command, HandlerMethod handlerMethod) {
        final String path = command.path();
        putCommand(path, handlerMethod);
    }

    @Override
    public HandlerMethod getHandlerByCommand(Command command) {
        if (command == null) {
            throw new NullPointerException("Command can't be null");
        }
        return getHandlerByCommand(command.path());
    }

    public void putSingleHandler(Class<? extends Annotation> annotation, HandlerMethod handlerMethod) {
        if (singleHandlerMap.containsKey(annotation)) {
            throw new RuntimeException("Annotation: " + annotation + " already registered");
        }

        singleHandlerMap.put(annotation, handlerMethod);
    }

    @Override
    public HandlerMethod getSingleHandler(Class<? extends Annotation> annotation) {
        final HandlerMethod handlerMethod = singleHandlerMap.get(annotation);
        if (handlerMethod == null) {
            throw new RuntimeException("Handler method for annotation: " + annotation + " not found");
        }

        return handlerMethod;
    }
}
