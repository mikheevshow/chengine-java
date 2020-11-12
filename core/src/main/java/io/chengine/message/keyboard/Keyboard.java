package io.chengine.message.keyboard;

import java.util.List;

public class Keyboard {

    private final List<KeyboardButton> buttons;

    private Keyboard(final List<KeyboardButton> buttons) {
        this.buttons = buttons;
    }

    public static KeyboardBuilder builder() {
        return new KeyboardBuilder();
    }

    public static class KeyboardBuilder {

        private List<KeyboardButton> buttons;

        public Keyboard build() {
            return new Keyboard(buttons);
        }

    }

    public List<KeyboardButton> getButtons() {
        return buttons;
    }
}
