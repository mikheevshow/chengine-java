package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramSendMessage implements Send {

    private String chatId;
    private String text;
    private String parseMode;
    private Integer replyMessageId;
    private Boolean disableNotification;
    private Boolean disableWebPagePreview;
    private Boolean allowSendingWithoutReply;
    private InlineKeyboard inlineKeyboard;

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

    public TelegramSendMessage disableNotification(Supplier<Boolean> disableNotification) {
        this.disableNotification = disableNotification.get();
        return this;
    }

    public TelegramSendMessage disableWebPagePreview(Supplier<Boolean> disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview.get();
        return this;
    }

    public TelegramSendMessage allowSendingWithoutReply(Supplier<Boolean> allowSendingWithoutReply) {
        this.allowSendingWithoutReply = allowSendingWithoutReply.get();
        return this;
    }

    public TelegramSendMessage withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        final InlineKeyboard.InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.accept(inlineKeyboardBuilder);
        this.inlineKeyboard = inlineKeyboardBuilder.build();
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

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public Boolean getDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public Boolean getAllowSendingWithoutReply() {
        return allowSendingWithoutReply;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }
}
