package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

public class TelegramSendAudioBotResponseConverter implements BotResponseConverter<SendDocument> {

    @Override
    public SendDocument convert(BotResponse response) {

        var sendDocument = new SendDocument();

        sendDocument.setCaption(response.message().text());
        sendDocument.setChatId(response.chat().id());
        sendDocument.setReplyMarkup(InlineKeyboardConverter.toTelegram(response.message().inlineKeyboard()));

        return sendDocument;
    }
}
