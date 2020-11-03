package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.method.Method;
import io.chengine.processor.response.ResponseTypeHandlerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MethodReturnedTypeProcessor implements ResponseResolver {

    private static final Logger log = LogManager.getLogger(MethodReturnedTypeProcessor.class);

    private final ResponseTypeHandlerFactory responseTypeHandlerFactory = new ResponseTypeHandlerFactory();

    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Method method, Object object) {
        var objClass = object.getClass();
        var handler = responseTypeHandlerFactory.get(objClass);
        if (handler != null) {
            handler.handle(method, object, botRequest, botResponse);
        } else { // just cast to string unknown type
            log.info("Unknown return type detection, cast response to String");
            var message = new Message(null, null, object.toString(), null, null);
            botResponse.setMessage(message);
        }
        botResponse.setChat(botRequest.chat());
    }
}