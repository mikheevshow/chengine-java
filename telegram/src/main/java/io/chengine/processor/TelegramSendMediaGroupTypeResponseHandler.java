package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendMediaGroup;
import io.chengine.method.HandlerMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramSendMediaGroupTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramSendMediaGroup.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAllowToProcess(
            HandlerMethod handlerMethod,
            BotRequestContext request,
            BotResponseContext response) {

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(
            HandlerMethod handlerMethod,
            ActionResponse returnedObject,
            BotRequestContext request,
            BotResponseContext response) {


        final TelegramSendMediaGroup mediaGroup = (TelegramSendMediaGroup) returnedObject;
        final Update update = request.get(Update.class);
        final Chat chat = update.getMessage().getChat();

        final SendMediaGroup sendMediaGroup = new SendMediaGroup();
        sendMediaGroup.setChatId(mediaGroup.getChatId() != null ? mediaGroup.getChatId() : chat.getId().toString());
        sendMediaGroup.setMedias(mediaGroup.getInputMedia());

        ((DefaultBotResponseContext) response).setResponseObject(sendMediaGroup);
    }
}
