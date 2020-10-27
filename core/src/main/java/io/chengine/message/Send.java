package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Send {

    private final String text;
    private final InlineKeyboard inlineKeyboard;

    private Send(
            final String text,
            final InlineKeyboard inlineKeyboard
    ) {
        this.text = text;
        this.inlineKeyboard = inlineKeyboard;
    }

    public static MessageBuilder message() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        private String text;
        private InlineKeyboard inlineKeyboard;

        public MessageBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public MessageBuilder withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
            var inlineKeyboardBuilder = InlineKeyboard.builder();
            inlineKeyboard.accept(inlineKeyboardBuilder);
            this.inlineKeyboard = inlineKeyboardBuilder.build();
            return this;
        }

        public Send done() {
            return new Send(
                    text,
                    inlineKeyboard
            );
        }
    }

    public String getText() {
        return text;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }
}
