package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendPhoto;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseHandler;

public class TelegramSendPhotoTypeResponseHandler extends AbstractActionResponseHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramSendPhoto.class;
    }

    @Override
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response) {
        return true;
    }

    @Override
    protected void process(
            HandlerMethod handlerMethod,
            ActionResponse returnedObject,
            BotRequestContext request,
            BotResponseContext response) {


    }
}
