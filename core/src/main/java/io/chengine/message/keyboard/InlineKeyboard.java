package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InlineKeyboard {

    private InlineKeyboard() {}

    public static InlineKeyboardBuilder builder() {
        return new InlineKeyboardBuilder();
    }

    public static class InlineKeyboardBuilder {

        private List<InlineKeyboardRow> rows = new ArrayList<>();

        public InlineKeyboardBuilder addRow(Consumer<InlineKeyboardRow.InlineKeyboardRowBuilder> row) {
            var rowBuilder = new InlineKeyboardRow.InlineKeyboardRowBuilder();
            row.accept(rowBuilder);
            rows.add(rowBuilder.build());
            return this;
        }

        public InlineKeyboard build() {
            return new InlineKeyboard();
        }

    }

}
