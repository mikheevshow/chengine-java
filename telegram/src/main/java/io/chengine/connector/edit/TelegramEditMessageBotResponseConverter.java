package io.chengine.connector.edit;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class TelegramEditMessageBotResponseConverter implements BotResponseConverter<EditMessageText> {

    @Override
    public EditMessageText convert(BotResponse response) {
        var editMessageText = new EditMessageText();
        editMessageText.setChatId(response.chat().id());
        editMessageText.setMessageId((int) response.message().id());
        editMessageText.setText(response.message().text());
        editMessageText.setReplyMarkup(InlineKeyboardConverter.toTelegram(response.message().inlineKeyboard()));

        return editMessageText;
    }
}
