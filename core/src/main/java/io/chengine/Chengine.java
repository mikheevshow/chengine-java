package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.context.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.pipeline.processor.DefaultPipelineRequestHandler;
import io.chengine.pipeline.processor.PipelineRequestHandler;
import io.chengine.processor.*;
import io.chengine.session.ChengineSessionContext;
import io.chengine.session.SessionCache;
import io.chengine.session.SessionManager;
import io.chengine.session.UserPipelineSessionInfo;
import io.chengine.session.pipeline.PipelineSessionManager;

import javax.annotation.PostConstruct;
import java.util.List;

// TODO
public class Chengine {

    private CommandMethodResolver commandMethodResolver; // <- перенесено (инициализировать)
    private SessionManager<UserPipelineSessionInfo> userPipelineSessionInfoSessionManager;
    private SessionCache<UserPipelineSessionInfo> sessionCache;
    private PipelineRequestHandler pipelineRequestHandler;
    private MessageResolverFactory messageResolverFactory; // <- перенесено (инициализировать)
    private MethodReturnedTypeProcessor methodReturnedTypeProcessor;
    private MethodArgumentInspector methodArgumentInspector; // <- перенесено (инициализировать)
    private MessageProcessor<BotRequest, BotResponse> messageProcessor;
    private HandlerRegistry handlerRegistry;
    private List<RequestHandler> botList;

    private Chengine() {
    }

    public Chengine(ChengineConfiguration configuration) {
        this.handlerRegistry = new ChengineHandlerContext(configuration);
    }

    @PostConstruct
    private void init() {

        this.commandMethodResolver = new CommandMethodResolver(this.handlerRegistry);
        this.sessionCache = new ChengineSessionContext<>();
        this.userPipelineSessionInfoSessionManager = new PipelineSessionManager(this.sessionCache);
        this.pipelineRequestHandler = new DefaultPipelineRequestHandler();
        this.messageResolverFactory = new MessageResolverFactory(this.commandMethodResolver);
        this.methodReturnedTypeProcessor = new MethodReturnedTypeProcessor();
        this.methodArgumentInspector = new MethodArgumentInspector();
        this.messageProcessor = new ChengineMessageProcessor(
                this.commandMethodResolver,
                this.methodArgumentInspector,
                this.methodReturnedTypeProcessor,
                this.userPipelineSessionInfoSessionManager,
                this.pipelineRequestHandler
        );

        this.botList.forEach(bot -> bot.setMessageProcessor(messageProcessor));

    }
}
