package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;

public class TelegramSendLocationBotResponseConverter implements BotResponseConverter<SendLocation> {

    @Override
    public SendLocation convert(BotResponse response) {

        var sendLocation = new SendLocation();

        sendLocation.setChatId(response.chat().id());
        sendLocation.setLatitude(response.message().location().latitude());
        sendLocation.setLongitude(response.message().location().latitude());
        sendLocation.setReplyMarkup(InlineKeyboardConverter.toTelegram(response.message().inlineKeyboard()));

        return sendLocation;
    }
}
