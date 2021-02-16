package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramEditMessageText;
import io.chengine.method.HandlerMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramEditMessageTextTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends ActionResponse> supports() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAllowToProcess(
            HandlerMethod handlerMethod,
            BotRequestContext request,
            BotResponseContext response) {

        return false;
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

        final TelegramEditMessageText messageText = (TelegramEditMessageText) returnedObject;
        final Update update = (Update) request.get(Update.class);
        final Chat chat = update.getMessage().getChat();
        final Message message = update.getMessage();

        final String chatId = messageText.getChatId() != null ? messageText.getChatId().toString() : chat.getId().toString();
        final Integer messageId = messageText.getMessageId() != null ? messageText.getMessageId() : message.getMessageId();
        final String newText = messageText.getText() != null ? messageText.getText() : message.getText();

        final EditMessageText editMessageText = new EditMessageText();
        editMessageText.setText(newText);
        editMessageText.setParseMode(messageText.getParseMode());
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);

        ((DefaultBotResponseContext) response).setResponseObject(editMessageText);
    }
}
