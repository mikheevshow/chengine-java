package io.chengine.message.keyboard;

import java.util.function.Supplier;

public class KeyboardButton {

    private final String text;

    private KeyboardButton(final String text) {
        this.text = text;
    }

    public static KeyboardButtonBuilder builder() {
        return new KeyboardButtonBuilder();
    }

    public static class KeyboardButtonBuilder {

        private String text;

        public KeyboardButton.KeyboardButtonBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public KeyboardButton build() {
            return new KeyboardButton(text);
        }
    }

    public String getText() {
        return text;
    }
}
