package io.chengine.message;

public interface TelegramSend {

    static TelegramSendAnimation animation() {
        return new TelegramSendAnimation();
    }

    static TelegramSendAudio audio() {
        return new TelegramSendAudio();
    }

    static TelegramSendChatAction chatAction() {
        return new TelegramSendChatAction();
    }

    static TelegramSendContact contact() {
        return new TelegramSendContact();
    }

    static TelegramSendDice dice() {
        return new TelegramSendDice();
    }

    static TelegramSendDocument document() {
        return new TelegramSendDocument();
    }

    static TelegramSendGame game() {
        return new TelegramSendGame();
    }

    static TelegramSendInvoice invoice() {
        return new TelegramSendInvoice();
    }

    static TelegramSendLocation location() {
        return new TelegramSendLocation();
    }

    static TelegramSendMediaGroup mediaGroup() {
        return new TelegramSendMediaGroup();
    }

    static TelegramSendMessage message() {
        return new TelegramSendMessage();
    }

    static TelegramSendPhoto photo() {
        return new TelegramSendPhoto();
    }

    static TelegramSendSticker sticker() {
        return new TelegramSendSticker();
    }

    static TelegramSendVenue venue() {
        return new TelegramSendVenue();
    }

    static TelegramSendVideo video() {
        return new TelegramSendVideo();
    }

    static TelegramSendVideoNote videoNote() {
        return new TelegramSendVideoNote();
    }

    static TelegramSendVoice voice() {
        return new TelegramSendVoice();
    }

    static TelegramSendPoll poll() {
        return new TelegramSendPoll();
    }

    static TelegramSendStopPoll stopPoll() {
        return new TelegramSendStopPoll();
    }

}
