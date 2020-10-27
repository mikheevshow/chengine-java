package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InlineKeyboard {

    private final List<InlineKeyboardRow> rows;

    private InlineKeyboard(

            final List<InlineKeyboardRow> rows

    ) {

        this.rows = rows;

    }

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
            return new InlineKeyboard(
                    rows
            );
        }
    }

    public List<InlineKeyboardRow> getRows() {
        return rows;
    }
}
