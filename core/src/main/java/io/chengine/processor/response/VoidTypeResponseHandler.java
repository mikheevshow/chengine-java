package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.Method;

public final class VoidTypeResponseHandler extends AbstractActionResponseHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> supports() {
        return Void.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(Method method, Object o, BotRequest request, BotResponse response) {
        response.setSend(false);
    }
}
