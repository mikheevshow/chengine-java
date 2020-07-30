package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.context.SecurityContext;

public class ChengineMethodSecurityProxy implements MessageProcessor<BotRequest, BotResponse> {

    private final ChengineMessageProcessor chengineMessageProcessor;
    private final SecurityContext securityContext;

    public ChengineMethodSecurityProxy(ChengineMessageProcessor chengineMessageProcessor, SecurityContext securityContext) {
        this.chengineMessageProcessor = chengineMessageProcessor;
        this.securityContext = securityContext;
    }

    @Override
    public void process(BotRequest request, BotResponse response) throws Exception {
        if (request.message().containsCommand() && !securityContext.methodAvailableForApi(request.identifier())) {
            throw new RuntimeException("Метод не доступен");
        }
        chengineMessageProcessor.process(request, response);
    }
}
