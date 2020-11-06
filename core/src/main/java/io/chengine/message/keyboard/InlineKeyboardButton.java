package io.chengine.message.keyboard;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class InlineKeyboardButton {

    private final String text;
    private final String url;
    private final String data;
    private final boolean pay;

    private InlineKeyboardButton(
            String text,
            String url,
            String data,
            boolean pay) {
        this.text = text;
        this.url = url;
        this.data = data;
        this.pay = pay;
    }

    public static InlineKeyboardButtonBuilder builder() {
        return new InlineKeyboardButtonBuilder();
    }

    public static class InlineKeyboardButtonBuilder {

        private String text;
        private String url;
        private String data;
        private boolean pay;

        public InlineKeyboardButtonBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public InlineKeyboardButtonBuilder withUrl(Supplier<String> url) {
            this.url = url.get();
            return this;
        }


        public InlineKeyboardButtonBuilder withData(Supplier<String> data) {
            this.data = data.get();
            return this;
        }

        public InlineKeyboardButtonBuilder isPay(BooleanSupplier pay) {
            this.pay = pay.getAsBoolean();
            return this;
        }

        public InlineKeyboardButton build() {
            return new InlineKeyboardButton(text, url, data, pay);
        }
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public boolean isPay() {
        return pay;
    }
}
