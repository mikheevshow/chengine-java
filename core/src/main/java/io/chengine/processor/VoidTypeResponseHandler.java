package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;

public final class VoidTypeResponseHandler extends AbstractMethodReturnedValueHandler<Void> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Void> supports() {
        return Void.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(
            final HandlerMethod handlerMethod,
            final Void returnedObject,
            final BotRequestContext request,
            final BotResponseContext response) {

        // Do nothing
    }
}
