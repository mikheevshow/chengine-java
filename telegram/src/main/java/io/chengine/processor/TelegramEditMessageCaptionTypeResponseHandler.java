package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramEdit;
import io.chengine.message.TelegramEditMessageCaption;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseMethodReturnedValueHandler;

public class TelegramEditMessageCaptionTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramEditMessageCaption.class;
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

        final TelegramEdit telegramEdit = (TelegramEdit) returnedObject;
    }
}
