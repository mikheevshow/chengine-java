package io.chengine.message;

import io.chengine.connector.Message;
import io.chengine.message.attachment.Attachment;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.Keyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Send implements ActionResponse {

    private final Integer chatId;
    private final String text;
    private final String parseMode;
    private final Attachment attachment;
    private final InlineKeyboard inlineKeyboard;
    private final Keyboard keyboard;

    private Send(
            final Integer chatId,
            final String text,
            final String parseMode,
            final Attachment attachment,
            final InlineKeyboard inlineKeyboard,
            final Keyboard keyboard) {

        this.chatId = chatId;
        this.text = text;
        this.parseMode = parseMode;
        this.attachment = attachment;
        this.inlineKeyboard = inlineKeyboard;
        this.keyboard = keyboard;
    }

    public static MessageBuilder message() {
        return new MessageBuilder();
    }

    public static Send messageWithText(Supplier<String> text) {
        return Send.message().withText(text).done();
    }

    public static class MessageBuilder {

        private Integer chatId;
        private String text;
        private String parseMode;
        private Attachment attachment;
        private InlineKeyboard inlineKeyboard;
        private Keyboard keyboard;

        public MessageBuilder toChatWithId(int chatId) {
            this.chatId = chatId;
            return this;
        }

        public MessageBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public MessageBuilder usingParseMode(Supplier<String> parseMode) {
            this.parseMode = parseMode.get();
            return this;
        }

        public MessageBuilder withAttachment(Supplier<Attachment> attachment) {
            this.attachment = attachment.get();
            return this;
        }

        public MessageBuilder withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
            var inlineKeyboardBuilder = InlineKeyboard.builder();
            inlineKeyboard.accept(inlineKeyboardBuilder);
            this.inlineKeyboard = inlineKeyboardBuilder.build();
            return this;
        }

        public MessageBuilder withKeyboard(Consumer<Keyboard.KeyboardBuilder> keyboard) {
            var keyboardBuilder = Keyboard.builder();
            keyboard.accept(keyboardBuilder);
            this.keyboard = keyboardBuilder.build();
            return this;
        }

        public Send done() {
            return new Send(chatId, text, parseMode, attachment, inlineKeyboard, keyboard);
        }
    }

    public Integer getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public String getParseMode() {
        return parseMode;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }

    public Keyboard getKeyboard() {
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
                '}';
    }
}
