package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;

public class TelegramSendAudioBotResponseConverter implements BotResponseConverter<SendAudio> {

    @Override
    public SendAudio convert(BotResponse response) {

        var sendAudio = new SendAudio();

        sendAudio.setCaption(response.message().text());

        return sendAudio;
    }
}
