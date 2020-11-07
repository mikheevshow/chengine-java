package io.chengine.connector.edit;

import io.chengine.connector.BotResponseConverter;
import io.chengine.connector.ConverterFactory;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@SuppressWarnings("unchecked")
public class EditResponseConverterFactory implements ConverterFactory {

    private static final TelegramEditMessageBotResponseConverter TELEGRAM_EDIT_MESSAGE_BOT_RESPONSE_CONVERTER = new TelegramEditMessageBotResponseConverter();

    @Override
    public <T> BotResponseConverter<T> get(Class<T> tClass) {
        if (EditMessageText.class.equals(tClass)) {
            return (BotResponseConverter<T>) TELEGRAM_EDIT_MESSAGE_BOT_RESPONSE_CONVERTER;
        }

        return null;
    }
}
