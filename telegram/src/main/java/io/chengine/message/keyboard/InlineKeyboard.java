package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

        private final List<InlineKeyboardRow> rows = new ArrayList<>();

        public InlineKeyboardBuilder addRow(Consumer<InlineKeyboardRow.InlineKeyboardRowBuilder> row) {
            var rowBuilder = new InlineKeyboardRow.InlineKeyboardRowBuilder();
            row.accept(rowBuilder);
            rows.add(rowBuilder.build());
            return this;
        }

        public InlineKeyboardBuilder addRows(Supplier<List<InlineKeyboardRow>> keyboardRows) {
            rows.addAll(keyboardRows.get());
            return this;
        }

        public InlineKeyboard build() {
            return new InlineKeyboard(rows);
        }
    }

    public List<InlineKeyboardRow> getRows() {
        return rows;
    }

    public InlineKeyboardButton getButton(int rowIndex, int columnIndex) {
        if (rowIndex > rows.size() - 1) {
            throw new NoSuchButtonException("No button at row: " + rowIndex + ", and column: " + columnIndex);
        }
        var column = rows.get(rowIndex).getButtons();
        if (columnIndex > column.size()) {
            throw new NoSuchButtonException("No button at row: " + rowIndex + ", and column: " + columnIndex);
        }
        return column.get(columnIndex);
    }

    public void setButton(int rowIndex, int columnIndex, InlineKeyboardButton button) {
        getButton(rowIndex, columnIndex); // check existence
        rows.get(rowIndex).getButtons().set(columnIndex, button);
    }
}
