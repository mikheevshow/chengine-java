package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendPhoto;
import io.chengine.method.HandlerMethod;
import io.chengine.processor.response.AbstractActionResponseMethodReturnedValueHandler;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramSendPhotoTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

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

        final TelegramSendPhoto telegramSendPhoto  = (TelegramSendPhoto) returnedObject;
        final DefaultBotResponseContext defaultBotResponseContext = (DefaultBotResponseContext) response;
        final Update update = (Update) request.get(Update.class);
        final Chat chat = update.getMessage().getChat();

        final SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(telegramSendPhoto.getChatId() != null ? telegramSendPhoto.getChatId() : chat.getId().toString());
        sendPhoto.setCaption(telegramSendPhoto.getCaption());
        sendPhoto.setParseMode(telegramSendPhoto.getParseMode());
        sendPhoto.setPhoto(telegramSendPhoto.getInputFile());
        sendPhoto.setReplyMarkup(InlineKeyboardConverter.toTelegram(telegramSendPhoto.getInlineKeyboard()));

        defaultBotResponseContext.setResponseObject(sendPhoto);
    }
}
