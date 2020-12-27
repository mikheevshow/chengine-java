package io.chengine.message.keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class KeyboardColumn {

    private KeyboardColumn() {}

    public static KeyboardColumnBuilder builder() {
        return new KeyboardColumnBuilder();
    }

    public static class KeyboardColumnBuilder {

        private List<KeyboardButton> buttons = new ArrayList<>();

        public KeyboardColumnBuilder addButtonWithText(Supplier<String> text) {
            return this;
        }

        /**
         * Transform column into rows list
         * @return rows list
         */
        public List<KeyboardRow> buildRows() {
            return Collections.emptyList();
        }
    }

}
