package io.chengine.config;

import io.chengine.MessageProcessorAware;
import io.chengine.annotation.AnnotationProcessor;
import io.chengine.commons.Converter;
import io.chengine.processor.AbstractActionResponseHandler;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChengineConfig {

    private final List<Object> handlers = new ArrayList<>();
    private final Set<Class<? extends Annotation>> customHandlerAnnotations = new HashSet<>();
    private final List<AnnotationProcessor> annotationProcessors = new ArrayList<>();
    private final List<Converter<?, ?>> converters = new ArrayList<>();
    private final List<MessageProcessorAware> messageProcessorAwares = new ArrayList<>();
    private final List<AbstractActionResponseHandler> actionResponseHandlers = new ArrayList<>();

    public ChengineConfig addHandlers(List<Object> handlers) {
        this.handlers.addAll(handlers);
        return this;
    }

    public ChengineConfig addAnnotationProcessors(List<AnnotationProcessor> annotationProcessors) {
        this.annotationProcessors.addAll(annotationProcessors);
        return this;
    }

    public ChengineConfig addConverters(List<Converter<?, ?>> converters) {
        this.converters.addAll(converters);
        return this;
    }

    public ChengineConfig addCustomHandlerAnnotation(Class<? extends Annotation> annotation) {
        customHandlerAnnotations.add(annotation);
        return this;
    }

    public ChengineConfig addMessageProcessorAwares(List<MessageProcessorAware> messageProcessorAwares) {
        this.messageProcessorAwares.addAll(messageProcessorAwares);
        return this;
    }

    public ChengineConfig addResponseHandlerProviders(List<AbstractActionResponseHandler> actionResponseHandlers) {
        this.actionResponseHandlers.addAll(actionResponseHandlers);
        return this;
    }

    public List<Object> getHandlers() {
        return new ArrayList<>(handlers);
    }

    public List<AnnotationProcessor> getAnnotationProcessors() {
        return new ArrayList<>(annotationProcessors);
    }

    public List<Converter<?, ?>> getConverters() {
        return new ArrayList<>(this.converters);
    }

    public Set<Class<? extends Annotation>> getCustomHandlerAnnotations() {
        return new HashSet<>(customHandlerAnnotations);
    }

    public List<MessageProcessorAware> getMessageProcessorAwares() {
        return new ArrayList<>(messageProcessorAwares);
    }

    public List<AbstractActionResponseHandler> getActionResponseHandlers() {
        return new ArrayList<>(actionResponseHandlers);
    }
}
