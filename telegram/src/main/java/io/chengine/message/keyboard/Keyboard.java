package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Keyboard {

    private final List<KeyboardRow> rows;

    private Keyboard(final List<KeyboardRow> rows) {
        this.rows = rows;
    }

    public static KeyboardBuilder builder() {
        return new KeyboardBuilder();
    }

    public static class KeyboardBuilder {

        private final List<KeyboardRow> rows = new ArrayList<>();

        public KeyboardBuilder addColumn(Consumer<KeyboardColumn.KeyboardColumnBuilder> column) {
            final KeyboardColumn.KeyboardColumnBuilder columnBuilder = new KeyboardColumn.KeyboardColumnBuilder();
            column.accept(columnBuilder);
            rows.addAll(columnBuilder.buildRows());
            return this;
        }

        public KeyboardBuilder addRow(Consumer<KeyboardRow.KeyboardRowBuilder> row) {
            var rowBuilder = new KeyboardRow.KeyboardRowBuilder();
            row.accept(rowBuilder);
            rows.add(rowBuilder.build());
            return this;
        }

        public Keyboard build() {
            return new Keyboard(rows);
        }

    }

    public List<KeyboardRow> getRows() {
        return rows;
    }
}
