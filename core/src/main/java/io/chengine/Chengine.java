package io.chengine;

import io.chengine.handler.HandlerVisitorRegistry;

import java.util.Properties;

public class Chengine {

    private HandlerVisitorRegistry handlerVisitorRegistry;

//    private CommandMethodResolver commandMethodResolver; // <- перенесено (инициализировать)
//    private SessionManager<UserPipelineSessionInfo> userPipelineSessionInfoSessionManager;
//    private SessionCache<UserPipelineSessionInfo> sessionCache;
//    private PipelineRequestHandler pipelineRequestHandler;
//    private MessageResolverFactory messageResolverFactory; // <- перенесено (инициализировать)
//    private MethodReturnedTypeProcessor methodReturnedTypeProcessor;
//    private MethodArgumentInspector methodArgumentInspector; // <- перенесено (инициализировать)
//    private MessageProcessor<BotRequest, BotResponse> messageProcessor;
//    private HandlerRegistry handlerRegistry;
//    private List<RequestHandler> botList;
//
//    private Chengine() {
//    }
//
//    public Chengine(Properties configuration) {
//        this.handlerRegistry = new ChengineHandlerContext(configuration);
//        this.botList = (List<RequestHandler>) configuration.get(REQUEST_HANDLERS_AWARE);
//    }
//
//    @PostConstruct
//    private void init() {
//
//        this.commandMethodResolver = new CommandMethodResolver(this.handlerRegistry);
//        this.sessionCache = new ChengineSessionContext<>();
//        this.userPipelineSessionInfoSessionManager = new PipelineSessionManager(this.sessionCache);
//        this.pipelineRequestHandler = new DefaultPipelineRequestHandler();
//        this.messageResolverFactory = new MessageResolverFactory(this.commandMethodResolver);
//        this.methodReturnedTypeProcessor = new MethodReturnedTypeProcessor();
//        this.methodArgumentInspector = new MethodArgumentInspector();
//        this.messageProcessor = new ChengineMessageProcessor(
//                this.messageResolverFactory,
//                this.methodArgumentInspector,
//                this.methodReturnedTypeProcessor,
//                this.userPipelineSessionInfoSessionManager,
//                this.pipelineRequestHandler
//        );
//
//        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));
//
//    }

    public Chengine(Properties properties) {
        initializeHandlerVisitorRegistry();
    }

    private void initializeHandlerVisitorRegistry() {

    }
}
