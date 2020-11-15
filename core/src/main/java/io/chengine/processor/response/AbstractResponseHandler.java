package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractResponseHandler<T> implements MethodReturnedValueHandler<T>{

    protected static final Logger log = LogManager.getLogger(AbstractActionResponseHandler.class);

    public abstract Class<? extends T> supports();

    @Override
    public void handle(HandlerMethod handlerMethod, T returnedObject, BotRequest request, BotResponse response) {
        log.info("Process returned object of type: {}", this::supports);
        if (isAllowToProcess(handlerMethod, request, response)) {
            process(handlerMethod, returnedObject, request, response);
        } else {
            log.info("Process ActionResponse is not allowed.");
        }
    }

    protected abstract boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequest request, BotResponse response);

    /**
     * Implement this method to provide specified response type processing
     *
     * @param handlerMethod - called method
     * @param returnedObject - object returned by method
     * @param request - bot request object
     * @param response - bot response object
     */
    protected abstract void process(HandlerMethod handlerMethod, T returnedObject, BotRequest request, BotResponse response);

}
