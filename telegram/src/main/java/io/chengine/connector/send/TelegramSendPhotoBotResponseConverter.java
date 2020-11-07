package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class TelegramSendPhotoBotResponseConverter implements BotResponseConverter<SendPhoto> {

    @Override
    public SendPhoto convert(BotResponse response) {

        var sendPhoto = new SendPhoto();

        sendPhoto.setCaption(response.message().text());
        sendPhoto.setChatId(response.chat().id());
        sendPhoto.setParseMode(response.message().parseMode());

        var chengineInlineKeyboard = response.message().inlineKeyboard();
        var telegramKeyboard = InlineKeyboardConverter.toTelegram(chengineInlineKeyboard);
        sendPhoto.setReplyMarkup(telegramKeyboard);


        return sendPhoto;
    }

}
