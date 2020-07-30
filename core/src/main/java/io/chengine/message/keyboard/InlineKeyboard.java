package io.chengine.message.keyboard;

import io.chengine.message.Send;
import io.chengine.message.keyboard.InlineKeyboardRow.InlineKeyboardRowBuilder;

import java.util.List;
import java.util.function.Consumer;

public class InlineKeyboard extends Keyboard {

    private final List<InlineKeyboardRow> rows;

    public InlineKeyboard(List<InlineKeyboardRow> rows) {
        this.rows = rows;
    }

    public static InlineKeyboardBuilder builder() {
       // return new InlineKeyboardBuilder();
    }

    public static class InlineKeyboardBuilder {

        private final Send.MessageBuilder parentBuilder;

        public InlineKeyboardBuilder(Send.MessageBuilder parentBuilder) {
            this.parentBuilder = parentBuilder;
        }

        public InlineKeyboardBuilder add(Consumer<InlineKeyboardRowBuilder> row) {
            return this;
        }

        public Send.MessageBuilder then() {
            return parentBuilder;
        }
    }

}
