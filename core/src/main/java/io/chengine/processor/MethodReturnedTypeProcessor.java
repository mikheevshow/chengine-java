package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.ResponseTypeHandlerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public class MethodReturnedTypeProcessor implements ResponseResolver<Object> {

    private static final Logger log = LogManager.getLogger(MethodReturnedTypeProcessor.class);
    private final ResponseResolver<ActionResponse> actionResponseResponseResolver = new ActionResponseResolver();

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolve(BotRequest request, BotResponse response, HandlerMethod handlerMethod, Object object) {
        if (object != null) {
            if (object instanceof ActionResponse) {
                actionResponseResponseResolver.resolve(request, response, handlerMethod, (ActionResponse) object);
            } else { // just cast to string unknown type
                log.info("Return type handler not found, cast response to String.class");
                var message = new Message(null, null, object.toString(), null, null);
                response.setResponseStrategy(SEND_MESSAGE);
                response.setMessage(message);
            }
            response.setChat(request.chat());
        }
    }
}