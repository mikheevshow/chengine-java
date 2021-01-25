package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard.InlineKeyboardBuilder;

import java.util.function.Consumer;

public class TelegramEditMessageCaption extends TelegramAbstractEdit {

    private String messageId;
    private String inlineMessageId;
    private String chatId;

    private String caption;
    private String parseMode;


    public TelegramEditMessageCaption setInlineKeyboard(Consumer<InlineKeyboardBuilder> inlineKeyboard) {
        setInlineKeyboardInternal(inlineKeyboard);
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getCaption() {
        return caption;
    }

    public String getParseMode() {
        return parseMode;
    }

}
