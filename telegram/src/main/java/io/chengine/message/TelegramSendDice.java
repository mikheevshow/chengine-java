package io.chengine.message;

import java.util.function.Supplier;

public class TelegramSendDice implements Send {

    private String chatId;
    private Integer value;
    private String emoji;

    public TelegramSendDice toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendDice hasValue(Supplier<Integer> value) {
        this.value = value.get();
        return this;
    }

    public TelegramSendDice usingEmoji(Supplier<String> emoji) {
        this.emoji = emoji.get();
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public Integer getValue() {
        return value;
    }

    public String getEmoji() {
        return emoji;
    }
}
