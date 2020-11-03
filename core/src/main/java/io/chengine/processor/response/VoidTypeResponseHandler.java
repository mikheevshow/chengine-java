package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.Method;

public class VoidTypeResponseHandler extends AbstractResponseTypeHandler {

    @Override
    public Class<?> supports() {
        return Void.TYPE;
    }

    @Override
    protected void process(Method method, Object o, BotRequest request, BotResponse response) {
        response.setSend(false);
    }
}
