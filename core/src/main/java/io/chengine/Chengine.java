package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.context.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.pipeline.processor.PipelineRequestHandler;
import io.chengine.processor.*;
import io.chengine.session.SessionManager;
import io.chengine.session.UserPipelineSessionInfo;

import javax.annotation.PostConstruct;
import java.util.List;

// TODO
public class Chengine {

//    private MethodResolver commandMethodResolver = CommandMethodResolver(); // <- перенесено (инициализировать)
//    private SessionManager<UserPipelineSessionInfo> userPipelineSessionInfoSessionManager;
//    private PipelineRequestHandler pipelineRequestHandler;
//    private MessageResolverFactory messageResolverFactory; // <- перенесено (инициализировать)
//    private MethodReturnedTypeProcessor methodReturnedTypeProcessor;
//    private MethodArgumentInspector methodArgumentInspector; // <- перенесено (инициализировать)
//    private MessageProcessor<BotRequest, BotResponse> messageProcessor;
//    private HandlerRegistry handlerRegistry;
//    private List<RequestHandler> botList;

    private Chengine() {}

    public Chengine(ChengineConfiguration configuration) {

//        this.handlerRegistry = new ChengineHandlerContext(configuration);

    }

    @PostConstruct
    private void init() {
        initMethodResolvers();
        setMessageProcessorForBots();
    }

    private void initMethodResolvers() {
//        this.commandMethodResolver = new CommandMethodResolver(handlerRegistry);
    }


    private void setMessageProcessorForBots() {
//        botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
    }

}
