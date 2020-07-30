package io.chengine.message.keyboard;

import java.util.List;

public class ReplyKeyboard extends Keyboard {

    private final List<Row> rows;

    public ReplyKeyboard(List<Row> rows) {
        this.rows = rows;
    }

    private final static class Row {

    }

}
