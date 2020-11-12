package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.method.Method;
import io.chengine.processor.response.ResponseTypeHandlerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public class MethodReturnedTypeProcessor implements ResponseResolver {

    private static final Logger log = LogManager.getLogger(MethodReturnedTypeProcessor.class);

    private final ResponseTypeHandlerFactory responseTypeHandlerFactory = new ResponseTypeHandlerFactory();

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Method method, Object object) {
        if (object != null) {
            var objClass = object.getClass();
            var handler = responseTypeHandlerFactory.get(objClass);
            if (handler != null) {
                handler.handle(method, object, botRequest, botResponse);
            } else { // just cast to string unknown type
                log.info("Return type handler not found, cast response to String.class");
                var message = new Message(null, null, object.toString(), null, null);
                botResponse.setResponseStrategy(SEND_MESSAGE);
                botResponse.setMessage(message);
            }
            botResponse.setChat(botRequest.chat());
        }
    }
}