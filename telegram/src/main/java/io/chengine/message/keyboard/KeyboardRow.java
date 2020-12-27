package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KeyboardRow {

    private final List<KeyboardButton> buttons;

    public KeyboardRow(List<KeyboardButton> buttons) {
        this.buttons = buttons;
    }

    public KeyboardRow.KeyboardRowBuilder builder() {
        return new KeyboardRow.KeyboardRowBuilder();
    }

    public static class KeyboardRowBuilder {

        private final List<KeyboardButton> buttons = new ArrayList<>();

        public KeyboardRow.KeyboardRowBuilder addButton(Consumer<KeyboardButton.KeyboardButtonBuilder> button) {
            var buttonBuilder = KeyboardButton.builder();
            button.accept(buttonBuilder);
            buttons.add(buttonBuilder.build());
            return this;
        }

        public KeyboardRow.KeyboardRowBuilder addButton(Supplier<KeyboardButton> button) {
            buttons.add(button.get());
            return this;
        }

        public KeyboardRow build() {
            return new KeyboardRow(buttons);
        }

    }

    public List<KeyboardButton> getButtons() {
        return buttons;
    }

}
