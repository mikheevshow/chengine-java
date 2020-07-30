package io.chengine.message.keyboard;

public class InlineKeyboardButton {

    private final String text;
    private final String data;

    public InlineKeyboardButton(String text, String data) {
        this.text = text;
        this.data = data;
    }

    public static InlineKeyboardButtonBuilder builder() {
        return new InlineKeyboardButtonBuilder();
    }

    public static class InlineKeyboardButtonBuilder {

        private String text;
        private String data;

        public InlineKeyboardButtonBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public InlineKeyboardButtonBuilder withData(String date) {
            this.data = date;
            return this;
        }
    }

    public String getText() {
        return text;
    }

    public String getData() {
        return data;
    }
}
