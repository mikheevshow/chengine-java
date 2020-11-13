package io.chengine.message;

import io.chengine.message.attachment.Attachment;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.Keyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Send implements ActionResponse {

    private Integer chatId;
    private String text;
    private String parseMode;
    private Attachment attachment;
    private InlineKeyboard inlineKeyboard;
    private Keyboard keyboard;

    public static Send message() {
        return new Send();
    }

    public static Send messageWithText(Supplier<String> text) {
        return Send.message().withText(text);
    }

    public Send toChatWithId(int chatId) {
        this.chatId = chatId;
        return this;
    }

    public Send withText(Supplier<String> text) {
        this.text = text.get();
        return this;
    }

    public Send usingParseMode(Supplier<String> parseMode) {
        this.parseMode = parseMode.get();
        return this;
    }

    public Send withAttachment(Supplier<Attachment> attachment) {
        this.attachment = attachment.get();
        return this;
    }

    public Send withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        var inlineKeyboardBuilder = InlineKeyboard.builder();
        inlineKeyboard.accept(inlineKeyboardBuilder);
        this.inlineKeyboard = inlineKeyboardBuilder.build();
        return this;
    }

    public Send withKeyboard(Consumer<Keyboard.KeyboardBuilder> keyboard) {
        var keyboardBuilder = Keyboard.builder();
        keyboard.accept(keyboardBuilder);
        this.keyboard = keyboardBuilder.build();
        return this;
    }

    public Integer chatId() {
        return chatId;
    }

    public String text() {
        return text;
    }

    public String parseMode() {
        return parseMode;
    }

    public Attachment attachment() {
        return attachment;
    }

    public InlineKeyboard inlineKeyboard() {
        return inlineKeyboard;
    }

    public Keyboard keyboard() {
        return keyboard;
    }

    @Override
    public String toString() {
        return "Send{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                ", parseMode='" + parseMode + '\'' +
                ", attachment=" + attachment +
                ", inlineKeyboard=" + inlineKeyboard +
                ", keyboard=" + keyboard +
                '}';
    }
}
