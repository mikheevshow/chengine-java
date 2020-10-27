package io.chengine.connector;

import io.chengine.message.keyboard.InlineKeyboard;

import javax.annotation.Nullable;

public class BotResponse {

    private Long chatId;

    private String text;

    @Nullable
    private InlineKeyboard inlineKeyboard;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String message) {
        this.text = message;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }

    public void setInlineKeyboard(InlineKeyboard inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }
}
