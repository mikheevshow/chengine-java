package io.chengine.message;

public interface TelegramEdit {

    static TelegramEditMessageCaption caption() {
        return new TelegramEditMessageCaption();
    }

    static TelegramEditMessageLiveLocation liveLocation() {
        return new TelegramEditMessageLiveLocation();
    }

    static TelegramEditMessageMedia media() {
        return new TelegramEditMessageMedia();
    }

    static TelegramEditMessageReplyMarkup replyMarkup() {
        return new TelegramEditMessageReplyMarkup();
    }

    static TelegramEditMessageText text() {
        return new TelegramEditMessageText();
    }

}
