package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.AbstractActionResponseHandler;
import io.chengine.processor.MethodReturnedValueHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Inherit this class if you want to provide custom type processing
 *
 * @param <T> - custom returned type
 */
public abstract class AbstractMethodReturnedValueHandler<T> implements MethodReturnedValueHandler<T> {

    protected static final Logger log = LogManager.getLogger(AbstractActionResponseHandler.class);

    public abstract Class<? extends T> supports();

    @Override
    public void handle(HandlerMethod handlerMethod, T returnedObject, BotRequestContext request, BotResponseContext response) {
        log.info("Process returned object of type: {}", this::supports);
        if (isAllowToProcess(handlerMethod, request, response)) {
            process(handlerMethod, returnedObject, request, response);
        } else {
            log.info("Process of {} class is not allowed.", returnedObject.getClass());
        }
    }

    protected abstract boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response);

    /**
     * Implement this method to provide specified response type processing
     *
     * @param handlerMethod - called method
     * @param returnedObject - object returned by the method
     * @param request - bot request object
     * @param response - bot response object
     */
    protected abstract void process(HandlerMethod handlerMethod, T returnedObject, BotRequestContext request, BotResponseContext response);

}
