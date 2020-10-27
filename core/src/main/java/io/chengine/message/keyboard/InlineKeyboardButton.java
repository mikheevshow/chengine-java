package io.chengine.message.keyboard;

import java.util.function.Supplier;

public class InlineKeyboardButton {

    private final String text;
    private final String data;

    public InlineKeyboardButton(String text, String data) {
        this.text = text;
        this.data = data;
    }

    public static InlineKeyboardButtonBuilder builder() {
        return new InlineKeyboardButtonBuilder();
    }

    public static class InlineKeyboardButtonBuilder {

        private String text;
        private String data;

        public InlineKeyboardButtonBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public InlineKeyboardButtonBuilder withData(Supplier<String> data) {
            this.data = data.get();
            return this;
        }

        public InlineKeyboardButton build() {
            return new InlineKeyboardButton(
                    text,
                    data
            );
        }
    }

    public String getText() {
        return text;
    }

    public String getData() {
        return data;
    }
}
