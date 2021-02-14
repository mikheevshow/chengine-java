package io.chengine.message;

import java.util.function.Supplier;

public class TelegramDelete implements Delete {

    private Integer chatId;
    private Integer messageId;

    protected TelegramDelete() {}

    public static TelegramDelete message() {
        return new TelegramDelete();
    }

    public TelegramDelete withChatId(Supplier<Integer> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramDelete withMessageId(Supplier<Integer> messageId) {
        this.messageId = messageId.get();
        return this;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

}
