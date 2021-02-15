package io.chengine.handler;

import io.chengine.command.Command;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.PipelineDefinition;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultHandlerRegistry implements HandlerRegistry {

    private final Map<String, HandlerMethod> commandHandlerMap = new HashMap<>();
    private final Map<Class<? extends Annotation>, HandlerMethod> singleHandlerMap = new HashMap<>();
    private final Map<String, PipelineDefinition> pipelineDefinitionMap = new HashMap<>();

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

    public void putPipeline(String name, PipelineDefinition pipelineDefinition) {
        if (name == null) {
            throw new IllegalArgumentException("Pipeline name can't be null");
        }
        if (pipelineDefinition == null) {
            throw new IllegalArgumentException("Pipeline definition can't be null");
        }
        if (pipelineDefinitionMap.containsKey(name)) {
            throw new RuntimeException("Pipeline with name: \"" + name + "\" already registered");
        }

        pipelineDefinitionMap.put(name, pipelineDefinition);
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

    @Override
    public PipelineDefinition getPipelineDefinitionByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Pipeline name can't be null");
        }
        final PipelineDefinition pipelineDefinition = pipelineDefinitionMap.get(name);
        if (pipelineDefinition == null) {
            throw new NullPointerException("Can't find pipeline with name: " + name);
        }

        return pipelineDefinitionMap.get(name);
    }

    @Override
    public PipelineDefinition getPipelineDefinitionByClass(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }

        return getPipelineDefinitionByName(clazz.getName());
    }
}
