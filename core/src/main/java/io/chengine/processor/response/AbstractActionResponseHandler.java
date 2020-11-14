package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.message.ActionResponse;
import io.chengine.method.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractActionResponseHandler implements ActionResponseHandler {

    protected static final Logger log = LogManager.getLogger(AbstractActionResponseHandler.class);

    public abstract Class<?> supports();

    public void handle(Method method, Object returnedObject, BotRequest request, BotResponse response) {
        log.info("Process returned object of type: {}", this::supports);
        process(method, returnedObject, request, response);
    }

    @Override
    public void handle(ActionResponse response) {

    }

    /**
     * Implement this method to provide specified response type processing
     *
     * @param method - called method
     * @param returnedObject - object returned by method
     * @param request - bot request object
     * @param response - bot response object
     */
    protected abstract void process(Method method, Object returnedObject, BotRequest request, BotResponse response);

}
