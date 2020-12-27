package io.chengine;

import io.chengine.commons.RequestTypeConverter;
import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.handler.HandlerRegistry;
import io.chengine.message.ActionResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.*;
import io.chengine.processor.response.AbstractActionResponseHandler;
import io.chengine.provider.RequestTypeConverterProvider;
import io.chengine.provider.ResponseTypeHandlerProvider;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

import static io.chengine.config.Configs.*;

public class Chengine {

    private final Properties configuration;

    private HandlerRegistry handlerRegistry;

    private MethodResolver methodResolver;
    private ResponseResolver<ActionResponse> actionResponseResolver;

    private ResponseTypeHandlerFactory responseTypeHandlerFactory;
    private List<AbstractActionResponseHandler> abstractActionResponseHandlers;

    private List<RequestTypeConverter> requestTypeConverters;

    private MethodResponseResolver methodResponseResolver;
    private MethodArgumentInspector methodArgumentInspector;
    private MessageProcessor<BotRequestContext, BotResponseContext> messageProcessor;

    private List<MessageProcessorAware> botList;

    public Chengine(Properties configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    private void init() {

        this.handlerRegistry = new DefaultHandlerRegistry(configuration);
        this.botList = (List<MessageProcessorAware>) configuration.get(REQUEST_HANDLERS_AWARE);

        this.methodResolver = new DefaultMethodResolver(this.handlerRegistry);

        final DefaultResponseTypeHandlerFactory responseTypeHandlerFactory = new DefaultResponseTypeHandlerFactory();

        this.abstractActionResponseHandlers = ((ResponseTypeHandlerProvider) configuration.get(RESPONSE_TYPE_HANDLER_AWARE)).provideAll();
        abstractActionResponseHandlers.forEach(h -> responseTypeHandlerFactory.put(h.supports(), h));
        this.responseTypeHandlerFactory = responseTypeHandlerFactory;

        this.requestTypeConverters = ((RequestTypeConverterProvider) configuration.get(REQUEST_TYPE_CONVERTER_AWARE)).provideAll();

        this.methodArgumentInspector = new MethodArgumentInspector();

        final ActionResponseResolver actionResponseResolver = new ActionResponseResolver(this.responseTypeHandlerFactory);
        this.methodResponseResolver = new MethodResponseResolver(actionResponseResolver);

        this.messageProcessor = new ChengineMessageProcessor(
                this.methodResolver,
                this.methodArgumentInspector,
                this.methodResponseResolver
        );

        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));

    }
}
