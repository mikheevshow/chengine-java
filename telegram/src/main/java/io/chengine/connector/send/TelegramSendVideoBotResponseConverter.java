package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

public class TelegramSendVideoBotResponseConverter implements BotResponseConverter<SendVideo> {

    @Override
    public SendVideo convert(BotResponse response) {

        var sendVideo = new SendVideo();

        sendVideo.setCaption(response.message().text());
        sendVideo.setChatId(response.chat().id());
        sendVideo.setParseMode(response.message().parseMode());

        return sendVideo;
    }
}
