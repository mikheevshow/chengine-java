package io.chengine.message.keyboard;

import io.chengine.message.Send;
import io.chengine.message.keyboard.InlineKeyboardButton.InlineKeyboardButtonBuilder;

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

        public InlineKeyboardRowBuilder add(Consumer<InlineKeyboardButtonBuilder> button) {
            return this;
        }

    }
}
