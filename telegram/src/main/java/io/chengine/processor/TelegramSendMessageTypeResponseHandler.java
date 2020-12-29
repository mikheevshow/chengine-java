package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendMessage;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseHandler;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;

public class TelegramSendMessageTypeResponseHandler extends AbstractActionResponseHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramSendMessage.class;
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

        final TelegramSendMessage telegramSend  = (TelegramSendMessage) returnedObject;
        final DefaultBotResponseContext defaultBotResponseContext = (DefaultBotResponseContext) response;
        final Chat chat = (Chat) request.get(Chat.class);

        final SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramSend.getChatId() != null ? telegramSend.getChatId() : chat.getId().toString());
        sendMessage.setText(telegramSend.getText());
        sendMessage.setParseMode(telegramSend.getParseMode());
        sendMessage.setDisableNotification(telegramSend.getDisableNotification());
        sendMessage.setDisableWebPagePreview(telegramSend.getDisableWebPagePreview());
        sendMessage.setAllowSendingWithoutReply(telegramSend.getAllowSendingWithoutReply());
        sendMessage.setReplyMarkup(InlineKeyboardConverter.toTelegram(telegramSend.getInlineKeyboard()));

        defaultBotResponseContext.setResponseObject(sendMessage);
    }
}
