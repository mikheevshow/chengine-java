package io.chengine.annotation;

import io.chengine.handler.DefaultHandlerRegistry;

public abstract class AbstractAnnotationProcessorHandlerRegistryAware implements AnnotationProcessor {

    protected DefaultHandlerRegistry handlerRegistry;

    public void setHandlerRegistry(DefaultHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }
}
