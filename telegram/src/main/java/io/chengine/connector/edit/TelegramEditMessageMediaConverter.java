package io.chengine.connector.edit;

import io.chengine.commons.Converter;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Chat;
import io.chengine.connector.Message;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class TelegramEditMessageMediaConverter implements Converter<BotResponse, EditMessageMedia> {

    @Override
    public EditMessageMedia convert(BotResponse from) {
        Message message = from.message();
        Chat chat = from.chat();
        EditMessageMedia editMessageMedia = new EditMessageMedia();
        editMessageMedia.setMessageId((int) message.id());
        editMessageMedia.setChatId(chat.id());
        InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
        inputMediaPhoto.setMedia("AgACAgIAAxkBAAIEm1_j4ih_89BpyT_a6i0WMZG_HcbzAAL4sDEbIJUhS3cNHjWE0psR2Kl0ly4AAwEAAwIAA3kAAyfjBAABHgQ");

        editMessageMedia.setMedia(inputMediaPhoto);

        return editMessageMedia;
    }
}
