package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramSendMessage extends TelegramAbstractSend {

    private String text;
    private String parseMode;
    private Boolean disableNotification;
    private Boolean disableWebPagePreview;
    private Boolean allowSendingWithoutReply;

    public TelegramSendMessage toChatWithId(Supplier<String> chatId) {
        toChatWithIdInternal(chatId);
        return this;
    }

    public TelegramSendMessage withText(Supplier<String> text) {
        this.text = validateSupplier(text);
        return this;
    }

    public TelegramSendMessage usingParseMode(Supplier<String> parseMode) {
        this.parseMode = validateSupplier(parseMode);
        return this;
    }

    public TelegramSendMessage disableNotification(Supplier<Boolean> disableNotification) {
        this.disableNotification = validateSupplier(disableNotification);
        return this;
    }

    public TelegramSendMessage disableWebPagePreview(Supplier<Boolean> disableWebPagePreview) {
        this.disableWebPagePreview = validateSupplier(disableWebPagePreview);
        return this;
    }

    public TelegramSendMessage allowSendingWithoutReply(Supplier<Boolean> allowSendingWithoutReply) {
        this.allowSendingWithoutReply = validateSupplier(allowSendingWithoutReply);
        return this;
    }

    public TelegramSendMessage withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        setInlineKeyboardInternal(inlineKeyboard);
        return this;
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

}
