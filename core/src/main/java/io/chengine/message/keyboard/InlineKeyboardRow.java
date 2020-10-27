package io.chengine.message.keyboard;

import io.chengine.message.keyboard.InlineKeyboardButton.InlineKeyboardButtonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InlineKeyboardRow {

    private final List<InlineKeyboardButton> buttons;

    public InlineKeyboardRow(List<InlineKeyboardButton> buttons) {
        this.buttons = buttons;
    }

    public InlineKeyboardRowBuilder builder() {
        return new InlineKeyboardRowBuilder();
    }

    public static class InlineKeyboardRowBuilder {

        private List<InlineKeyboardButton> buttons = new ArrayList<>();

        public InlineKeyboardRowBuilder addButton(Consumer<InlineKeyboardButtonBuilder> button) {
            var buttonBuilder = new InlineKeyboardButtonBuilder();
            button.accept(buttonBuilder);
            buttons.add(buttonBuilder.build());
            return this;
        }

        public InlineKeyboardRow build() {
            return new InlineKeyboardRow(null);
        }

    }

    public List<InlineKeyboardButton> getButtons() {
        return buttons;
    }
}
