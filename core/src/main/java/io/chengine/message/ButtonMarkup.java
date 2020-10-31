package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboardButton;

public class ButtonMarkup {

    private final int rowIndex;
    private final int columnIndex;
    private final InlineKeyboardButton button;

    public ButtonMarkup(int rowIndex, int columnIndex, InlineKeyboardButton button) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.button = button;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public InlineKeyboardButton getButton() {
        return button;
    }
}
