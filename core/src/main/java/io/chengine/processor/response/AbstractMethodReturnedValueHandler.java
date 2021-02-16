package io.chengine.processor.response;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

/**
 * Inherit this class if you want to provide custom type processing
 *
 * @param <T> - custom returned type
 */
public abstract class AbstractMethodReturnedValueHandler<T> implements MethodReturnedValueHandler<T> {

    protected static final Logger log = LogManager.getLogger(AbstractActionResponseMethodReturnedValueHandler.class);
    protected MethodReturnedValueHandler nextHandler;

    public abstract Class<? extends T> supports();

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void handle(
            final HandlerMethod handlerMethod,
            final T returnedObject,
            final BotRequestContext request,
            final BotResponseContext response) {

        log.info("Process returned object of type: {}", this::supports);
        if (isAllowToProcess(handlerMethod, request, response)) {
            process(handlerMethod, returnedObject, request, response);
            if (nextHandler != null) {
                final Object previousSetObject = response.getResponseObject();
                nextHandler.handle(handlerMethod, previousSetObject, request, response);
            }
        } else {
            log.info("Process of {} class is not allowed.", returnedObject.getClass());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNext(@Nullable MethodReturnedValueHandler<?> nextHandler) {
        this.nextHandler = nextHandler;
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
