package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseMethodReturnedValueHandler;

public class TelegramSendAudioTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return null;
    }

    @Override
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response) {
        return false;
    }

    @Override
    protected void process(HandlerMethod handlerMethod, ActionResponse returnedObject, BotRequestContext request, BotResponseContext response) {

    }
}
