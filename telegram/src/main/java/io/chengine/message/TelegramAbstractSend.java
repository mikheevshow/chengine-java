package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramAbstractSend implements Send {

    protected String chatId;
    protected String replyMessageId;
    protected InlineKeyboard inlineKeyboard;
    protected Boolean disableNotification;
    protected Boolean allowSendingWithoutReply;

    protected void toChatWithIdInternal(Supplier<String> chatId) {
        this.chatId = validateSupplier(chatId);
    }

    protected void setReplyMessageIdInternal(Supplier<String> replyMessageId) {
        this.replyMessageId = validateSupplier(replyMessageId);
    }

    protected void setInlineKeyboardInternal(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        final InlineKeyboard.InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.accept(inlineKeyboardBuilder);
        this.inlineKeyboard = inlineKeyboardBuilder.build();
    }

    protected void setDisableNotificationInternal(Supplier<Boolean> disableNotification) {
        this.disableNotification = validateSupplier(disableNotification);
    }

    public String getChatId() {
        return chatId;
    }

    public String getReplyMessageId() {
        return replyMessageId;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }

}
