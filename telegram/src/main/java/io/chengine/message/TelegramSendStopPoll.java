package io.chengine.message;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class TelegramSendStopPoll implements Send {

    private String chatId;
    private String messageId;
    private String deserializedResponse;

    protected TelegramSendStopPoll() {}

    public TelegramSendStopPoll toChatWithId(@Nonnull Supplier<String> chatId) {
        this.chatId = validateSupplier(chatId);
        return this;
    }

    public TelegramSendStopPoll setMessageId(@Nonnull Supplier<String> messageId) {
        this.messageId = validateSupplier(messageId);
        return this;
    }

    public TelegramSendStopPoll deserializedResponse(@Nonnull Supplier<String> answer) {
        this.deserializedResponse = validateSupplier(answer);
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getDeserializedResponse() {
        return deserializedResponse;
    }
}
