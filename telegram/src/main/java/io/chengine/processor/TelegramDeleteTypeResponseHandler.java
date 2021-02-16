package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramDelete;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseMethodReturnedValueHandler;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramDeleteTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramDelete.class;
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

        final TelegramDelete telegramDelete = (TelegramDelete) returnedObject;

        final DeleteMessage deleteMessage = new DeleteMessage();
        if (telegramDelete.getChatId() == null && telegramDelete.getMessageId() == null) {
            final Update update = request.get(Update.class);
            final Chat chat = update.getMessage().getChat();
            final Message message = update.getMessage();
            deleteMessage.setChatId(chat.getId().toString());
            deleteMessage.setMessageId(message.getMessageId());
        }

        ((DefaultBotResponseContext) response).setResponseObject(deleteMessage);
    }
}
