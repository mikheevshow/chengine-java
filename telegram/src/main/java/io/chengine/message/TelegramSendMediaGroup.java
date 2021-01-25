package io.chengine.message;

import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TelegramSendMediaGroup implements Send {

    private String chatId;
    private String replyMessageId;
    private final List<InputMedia> inputMedia = new ArrayList<>();
    private Boolean disableNotification;
    private Boolean allowSendingWithoutReply;

    public TelegramSendMediaGroup toChatWithId(@Nonnull Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendMediaGroup withReplyMessageId(@Nonnull Supplier<String> replyMessageId) {
        this.replyMessageId = validateSupplier(replyMessageId);
        return this;
    }

    public TelegramSendMediaGroup attachFiles(@Nonnull Supplier<List<InputMedia>> inputMedia) {
        this.inputMedia.addAll(inputMedia.get());
        return this;
    }

    public TelegramSendMediaGroup attachFile(@Nonnull Supplier<InputMedia> inputMedia) {
        this.inputMedia.add(inputMedia.get());
        return this;
    }

    public TelegramSendMediaGroup disableNotification(@Nonnull Supplier<Boolean> disableNotification) {
        this.disableNotification = validateSupplier(disableNotification);
        return this;
    }

    public TelegramSendMediaGroup allowSendingWithoutReply(@Nonnull Supplier<Boolean> allowSendingWithoutReply) {
        this.allowSendingWithoutReply = validateSupplier(allowSendingWithoutReply);
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public String getReplyMessageId() {
        return replyMessageId;
    }

    public List<InputMedia> getInputMedia() {
        return inputMedia;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public Boolean getAllowSendingWithoutReply() {
        return allowSendingWithoutReply;
    }
}
