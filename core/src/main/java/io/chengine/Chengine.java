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
import io.chengine.pipeline.action.CheckStageActionMethodReturnedValueHandler;
import io.chengine.pipeline.action.FireStageActionMethodReturnedValueHandler;
import io.chengine.pipeline.trigger.PipelineTriggerMethodReturnedValueHandler;
import io.chengine.pipeline.trigger.TriggerPipeline;
import io.chengine.processor.*;
import io.chengine.processor.response.AbstractActionResponseMethodReturnedValueHandler;
import io.chengine.processor.response.ActionResponseResolver;
import io.chengine.processor.response.DefaultResponseTypeHandlerFactory;
import io.chengine.processor.response.ResponseTypeHandlerFactory;
import io.chengine.session.DefaultSessionCache;
import io.chengine.session.SessionCache;
import io.chengine.session.SessionRequestInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chengine {

    private ChengineConfig configuration;
    private final DefaultHandlerRegistry handlerRegistry = new DefaultHandlerRegistry();
    private final SessionCache sessionCache = new DefaultSessionCache();

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

        processHandlers();
        this.botList = configuration.getMessageProcessorAwares();
        this.methodResolver = new DefaultMethodResolver(handlerRegistry);

        configureMethodReturnedValueHandlerFactory(configuration);
        configureRequestTypesConverters(configuration);

        this.methodArgumentInspector = new MethodArgumentInspector();
        ActionResponseResolver actionResponseResolver = new ActionResponseResolver(this.responseTypeHandlerFactory);
        this.methodResponseResolver = new MethodResponseResolver(actionResponseResolver);

        // Prepare message processor
        this.messageProcessor = new DefaultMessageProcessor(
                this.methodResolver,
                this.methodArgumentInspector,
                this.methodResponseResolver
        );

        configureRequestInterceptors(configuration);
        // ****************************

        ((BotRequestResponseMessageProcessorAware )this.responseTypeHandlerFactory.get(TriggerPipeline.class)).setMessageProcessor(this.messageProcessor);

        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
    }

    private void configureMethodReturnedValueHandlerFactory(ChengineConfig configuration) {
        DefaultResponseTypeHandlerFactory responseTypeHandlerFactory = new DefaultResponseTypeHandlerFactory();
        this.abstractActionResponseHandlers = configuration.getActionResponseHandlers();

        final PipelineTriggerMethodReturnedValueHandler pipelineTriggerMethodReturnedValueHandler =
                new PipelineTriggerMethodReturnedValueHandler();
        pipelineTriggerMethodReturnedValueHandler.setHandlerRegistry(handlerRegistry);
        pipelineTriggerMethodReturnedValueHandler.setSessionCache(sessionCache);
        this.abstractActionResponseHandlers.add(pipelineTriggerMethodReturnedValueHandler);

        final CheckStageActionMethodReturnedValueHandler checkStageActionMethodReturnedValueHandler =
                new CheckStageActionMethodReturnedValueHandler();
        checkStageActionMethodReturnedValueHandler.setSessionCache(sessionCache);
        responseTypeHandlerFactory.put(checkStageActionMethodReturnedValueHandler.supports(), checkStageActionMethodReturnedValueHandler);
        checkStageActionMethodReturnedValueHandler.setResponseTypeHandlerFactoryAware(responseTypeHandlerFactory);

        final FireStageActionMethodReturnedValueHandler fireStageActionMethodReturnedValueHandler =
                new FireStageActionMethodReturnedValueHandler();
        fireStageActionMethodReturnedValueHandler.setSessionCache(sessionCache);
        responseTypeHandlerFactory.put(fireStageActionMethodReturnedValueHandler.supports(), fireStageActionMethodReturnedValueHandler);
        fireStageActionMethodReturnedValueHandler.setResponseTypeHandlerFactoryAware(responseTypeHandlerFactory);

        abstractActionResponseHandlers.forEach(h -> responseTypeHandlerFactory.put(h.supports(), h));
        this.responseTypeHandlerFactory = responseTypeHandlerFactory;
    }

    private void configureRequestTypesConverters(ChengineConfig configuration) {
        configuration.getConverters().forEach(converter -> {
            List<RequestTypeConverter> requestTypeConverters = new ArrayList<>();
            if (converter instanceof RequestTypeConverter) {
                requestTypeConverters.add((RequestTypeConverter) converter);
            }
            this.requestTypeConverters = requestTypeConverters;
        });
    }

    private void configureRequestInterceptors(ChengineConfig configuration) {
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
    }

    private void processHandlers() {
        List<Object> handlers = this.configuration.getHandlers();
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
