package io.chengine.message;

import java.util.function.Supplier;

public class TelegramSendMessage implements Send {

    private String chatId;
    private String text;
    private String parseMode;

    public TelegramSendMessage toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendMessage withText(Supplier<String> text) {
        this.text = text.get();
        return this;
    }

    public TelegramSendMessage usingParseMode(Supplier<String> parseMode) {
        this.parseMode = parseMode.get();
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public String getParseMode() {
        return parseMode;
    }
}
