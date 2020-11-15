package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.HandlerMethod;

public final class VoidTypeResponseHandler extends AbstractResponseHandler<Void> {

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
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequest request, BotResponse response) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(HandlerMethod handlerMethod, Void returnedObject, BotRequest request, BotResponse response) {
        response.setSend(false);
    }

}
