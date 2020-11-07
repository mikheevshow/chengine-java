package io.chengine.connector.edit;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class TelegramEditMessageBotResponseConverter implements BotResponseConverter<EditMessageText> {

    @Override
    public EditMessageText convert(BotResponse response) {
        return new EditMessageText()
                .setChatId(response.chat().id())
                .setMessageId((int) response.message().id())
                .setText(response.message().text())
                .setReplyMarkup(InlineKeyboardConverter.toTelegram(response.message().inlineKeyboard()));
    }
}
