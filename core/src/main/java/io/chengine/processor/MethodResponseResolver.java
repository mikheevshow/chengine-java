package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MethodResponseResolver implements ResponseResolver<Object> {

    private static final Logger log = LogManager.getLogger(MethodResponseResolver.class);
    private final ResponseResolver<ActionResponse> actionResponseResponseResolver;

    public MethodResponseResolver(ResponseResolver<ActionResponse> actionResponseResponseResolver) {
        this.actionResponseResponseResolver = actionResponseResponseResolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolve(BotRequestContext request, BotResponseContext response, HandlerMethod handlerMethod, Object object) {
        if (object != null) {
            if (object instanceof ActionResponse) {
                actionResponseResponseResolver.resolve(request, response, handlerMethod, (ActionResponse) object);
            } else {
                log.error("Can't process message because method has unknown return type.");
            }
        }
    }
}