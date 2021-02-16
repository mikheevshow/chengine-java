package io.chengine;

import io.chengine.annotation.AnnotationProcessor;
import io.chengine.annotation.HandleCommandAnnotationProcessor;
import io.chengine.commons.RequestTypeConverter;
import io.chengine.config.ChengineConfig;
import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.handler.HandlerRegistryAware;
import io.chengine.interceptor.InterceptorChainFactory;
import io.chengine.message.ActionResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.annotation.PipelineAnnotationProcessor;
import io.chengine.pipeline.trigger.PipelineTriggerMethodReturnedValueHandler;
import io.chengine.processor.*;
import io.chengine.session.DefaultSessionCache;
import io.chengine.session.SessionCache;
import io.chengine.session.SessionRequestInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chengine {

    private ChengineConfig configuration;
    private final DefaultHandlerRegistry handlerRegistry = new DefaultHandlerRegistry();

    private MethodResolver methodResolver;
    private ResponseResolver<ActionResponse> actionResponseResolver;

    private ResponseTypeHandlerFactory responseTypeHandlerFactory;
    private List<AbstractActionResponseMethodReturnedValueHandler> abstractActionResponseHandlers;

    private List<RequestTypeConverter> requestTypeConverters;

    private MethodResponseResolver methodResponseResolver;
    private MethodArgumentInspector methodArgumentInspector;
    private DefaultMessageProcessor messageProcessor;

    private List<MessageProcessorAware> botList;

    public Chengine(ChengineConfig configuration) {
        this.configuration = configuration;
        processHandlers(configuration.getHandlers());
        this.botList = configuration.getMessageProcessorAwares();
        this.methodResolver = new DefaultMethodResolver(handlerRegistry);


        DefaultResponseTypeHandlerFactory responseTypeHandlerFactory = new DefaultResponseTypeHandlerFactory();
        this.abstractActionResponseHandlers = configuration.getActionResponseHandlers();
        final SessionCache sessionCache = new DefaultSessionCache();
        final PipelineTriggerMethodReturnedValueHandler pipelineTriggerMethodReturnedValueHandler =
                new PipelineTriggerMethodReturnedValueHandler();
        pipelineTriggerMethodReturnedValueHandler.setHandlerRegistry(handlerRegistry);
        pipelineTriggerMethodReturnedValueHandler.setSessionCache(sessionCache);
        this.abstractActionResponseHandlers.add(pipelineTriggerMethodReturnedValueHandler);

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

        // Prepare message processor
        this.messageProcessor = new DefaultMessageProcessor(
                this.methodResolver,
                this.methodArgumentInspector,
                this.methodResponseResolver
        );

        final SessionRequestInterceptor sessionRequestInterceptor = new SessionRequestInterceptor();
        sessionRequestInterceptor.setSessionCache(sessionCache);
        configuration.getSessionKeyExtractors()
                .forEach(sessionKeyExtractor ->
                        sessionRequestInterceptor.putSessionKeyExtractor(sessionKeyExtractor.support(), sessionKeyExtractor)
                );

        final InterceptorChainFactory requestInterceptorChainFactory = new InterceptorChainFactory(
                Collections.singletonList(sessionRequestInterceptor)
        );
        this.messageProcessor.setRequestInterceptorChain(requestInterceptorChainFactory);
        // ****************************


        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
    }

    private void processHandlers(List<Object> handlers) {
        // Prepare handleCommandAnnotationProcessor
        HandleCommandAnnotationProcessor handleCommandAnnotationProcessor = new HandleCommandAnnotationProcessor();
        PipelineAnnotationProcessor pipelineAnnotationProcessor = new PipelineAnnotationProcessor();

        configuration
                .getCustomHandlerAnnotations()
                .forEach(handleCommandAnnotationProcessor::addHandlerAnnotation);

        handleCommandAnnotationProcessor.setHandlerRegistry(handlerRegistry);
        pipelineAnnotationProcessor.setHandlerRegistry(handlerRegistry);
        // Prepare other processors
        List<AnnotationProcessor> annotationProcessors = configuration.getAnnotationProcessors();
        annotationProcessors.forEach(processor -> {
            if (processor instanceof HandlerRegistryAware) {
                HandlerRegistryAware a = (HandlerRegistryAware) processor;
                a.setHandlerRegistry(handlerRegistry);
            }
        });

        // Process handlers
        List<AnnotationProcessor> preparedAnnotationProcessors = new ArrayList<>();
        preparedAnnotationProcessors.add(handleCommandAnnotationProcessor);
        preparedAnnotationProcessors.add(pipelineAnnotationProcessor);
        preparedAnnotationProcessors.addAll(annotationProcessors);
        handlers.forEach(handler ->
                preparedAnnotationProcessors.forEach(processor -> processor.process(handler))
        );
    }
}
