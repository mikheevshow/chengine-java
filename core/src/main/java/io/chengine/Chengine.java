package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.context.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.*;

import javax.annotation.PostConstruct;
import java.util.List;

public class Chengine {

    private MethodResolver commandMethodResolver;
    private MessageResolverFactory messageResolverFactory;
    private MethodReturnedTypeProcessor methodReturnedTypeProcessor;
    private MethodArgumentInspector methodArgumentInspector;
    private MessageProcessor<BotRequest, BotResponse> messageProcessor;
    private HandlerRegistry handlerRegistry;
    private List<RequestHandler> botList;

    private Chengine() {}

    public Chengine(ChengineConfiguration configuration) {

        this.handlerRegistry = new ChengineHandlerContext(configuration);

    }

    @PostConstruct
    private void init() {
        initMethodResolvers();
        setMessageProcessorForBots();
    }

    private void initMethodResolvers() {
        this.commandMethodResolver = new CommandMethodResolver(handlerRegistry);
    }


    private void setMessageProcessorForBots() {
        botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
    }


    //	@Bean
//	public MethodArgumentInspector methodArgumentInspector() {
//		return new MethodArgumentInspector();
//	}

//	@Bean
//	public CommandMethodResolver commandMethodResolver(ChengineHandlerContext chengineHandlerContext) {
//		return new CommandMethodResolver(chengineHandlerContext);
//	}

//	@Bean
//	public ResponseResolver responseResolver() {
//		return new MethodReturnedTypeProcessor();
//	}

//	@Bean
//	public MessageResolverFactory messageResolverFactory(CommandMethodResolver commandMethodResolver) {
//		return new MessageResolverFactory(commandMethodResolver);
//	}

//	@Bean
//	public ChengineMessageProcessor chengineMessageProcessor(
//			CommandMethodResolver commandMethodResolver,
//			MethodArgumentInspector methodArgumentInspector,
//			ResponseResolver responseResolver
//
//	) {
//
//		return new ChengineMessageProcessor(
//				commandMethodResolver,
//				methodArgumentInspector,
//				responseResolver,
//			new PipelineSessionManager(new ChengineSessionContext()),
//			new DefaultPipelineRequestHandler()
//		);
//	}

}
