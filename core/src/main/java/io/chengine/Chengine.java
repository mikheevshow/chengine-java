package io.chengine;

import io.chengine.annotation.AbstractAnnotationProcessorHandlerRegistryAware;
import io.chengine.annotation.HandleCommandAnnotationProcessor;
import io.chengine.annotation.AnnotationProcessor;
import io.chengine.commons.RequestTypeConverter;
import io.chengine.config.ChengineConfig;
import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.message.ActionResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.*;
import io.chengine.processor.AbstractActionResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class Chengine {

    private ChengineConfig configuration;
    private final DefaultHandlerRegistry handlerRegistry = new DefaultHandlerRegistry();

    private MethodResolver methodResolver;
    private ResponseResolver<ActionResponse> actionResponseResolver;

    private ResponseTypeHandlerFactory responseTypeHandlerFactory;
    private List<AbstractActionResponseHandler> abstractActionResponseHandlers;

    private List<RequestTypeConverter> requestTypeConverters;

    private MethodResponseResolver methodResponseResolver;
    private MethodArgumentInspector methodArgumentInspector;
    private MessageProcessor<BotRequestContext, BotResponseContext> messageProcessor;

    private List<MessageProcessorAware> botList;

    public Chengine(ChengineConfig configuration) {
        this.configuration = configuration;
        processHandlers(configuration.getHandlers());
        this.botList = configuration.getMessageProcessorAwares();
        this.methodResolver = new DefaultMethodResolver(handlerRegistry);
        DefaultResponseTypeHandlerFactory responseTypeHandlerFactory = new DefaultResponseTypeHandlerFactory();
        this.abstractActionResponseHandlers = configuration.getActionResponseHandlers();
        abstractActionResponseHandlers.forEach(h -> responseTypeHandlerFactory.put(h.supports(), h));
        this.responseTypeHandlerFactory = responseTypeHandlerFactory;
        configuration.getConverters().forEach(converter -> {
            List<RequestTypeConverter> requestTypeConverters = new ArrayList<>();
            if (converter instanceof RequestTypeConverter) {
                requestTypeConverters.add((RequestTypeConverter) converter);
            }
            this.requestTypeConverters = requestTypeConverters;
        });
        this.methodArgumentInspector = new MethodArgumentInspector();
        ActionResponseResolver actionResponseResolver = new ActionResponseResolver(this.responseTypeHandlerFactory);
        this.methodResponseResolver = new MethodResponseResolver(actionResponseResolver);
        this.messageProcessor = new ChengineMessageProcessor(
                this.methodResolver,
                this.methodArgumentInspector,
                this.methodResponseResolver
        );
        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
    }

    private void processHandlers(List<Object> handlers) {
        // Prepare handleCommandAnnotationProcessor
        HandleCommandAnnotationProcessor handleCommandAnnotationProcessor = new HandleCommandAnnotationProcessor();
        configuration
                .getCustomHandlerAnnotations()
                .forEach(handleCommandAnnotationProcessor::addHandlerAnnotation);
        handleCommandAnnotationProcessor.setHandlerRegistry(handlerRegistry);

        // Prepare other processors
        List<AnnotationProcessor> annotationProcessors = configuration.getAnnotationProcessors();
        annotationProcessors.forEach(p -> {
            if (p instanceof AbstractAnnotationProcessorHandlerRegistryAware) {
                AbstractAnnotationProcessorHandlerRegistryAware a = (AbstractAnnotationProcessorHandlerRegistryAware) p;
                a.setHandlerRegistry(handlerRegistry);
            }
        });

        // Process handlers
        List<AnnotationProcessor> preparedAnnotationProcessors = new ArrayList<>();
        preparedAnnotationProcessors.add(handleCommandAnnotationProcessor);
        preparedAnnotationProcessors.addAll(annotationProcessors);
        handlers.forEach(handler ->
                preparedAnnotationProcessors.forEach(processor -> processor.process(handler))
        );
    }
}
