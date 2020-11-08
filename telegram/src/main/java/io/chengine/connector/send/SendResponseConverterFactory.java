package io.chengine.connector.send;

import io.chengine.connector.BotResponseConverter;
import io.chengine.connector.ConverterFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@SuppressWarnings("unchecked")
public class SendResponseConverterFactory implements ConverterFactory {

    private static final TelegramSendMessageBotResponseConverter TELEGRAM_SEND_MESSAGE_BOT_RESPONSE_CONVERTER = new TelegramSendMessageBotResponseConverter();
    private static final TelegramSendPhotoBotResponseConverter TELEGRAM_SEND_PHOTO_BOT_RESPONSE_CONVERTER = new TelegramSendPhotoBotResponseConverter();
    private static final TelegramSendAudioBotResponseConverter TELEGRAM_SEND_AUDIO_BOT_RESPONSE_CONVERTER = new TelegramSendAudioBotResponseConverter();
    private static final TelegramSendVideoBotResponseConverter TELEGRAM_SEND_VIDEO_BOT_RESPONSE_CONVERTER = new TelegramSendVideoBotResponseConverter();
    private static final TelegramSendDocumentBotResponseConverter TELEGRAM_SEND_DOCUMENT_BOT_RESPONSE_CONVERTER = new TelegramSendDocumentBotResponseConverter();
    private static final TelegramSendLocationBotResponseConverter TELEGRAM_SEND_LOCATION_BOT_RESPONSE_CONVERTER = new TelegramSendLocationBotResponseConverter();

    @Override
    public <T> BotResponseConverter<T> get(Class<T> tClass) {

        if (SendMessage.class.equals(tClass)) {
            return (BotResponseConverter<T>) TELEGRAM_SEND_MESSAGE_BOT_RESPONSE_CONVERTER;
        } else if (SendPhoto.class.equals(tClass)) {
            return (BotResponseConverter<T>) TELEGRAM_SEND_PHOTO_BOT_RESPONSE_CONVERTER;
        }

        return null;
    }
}
