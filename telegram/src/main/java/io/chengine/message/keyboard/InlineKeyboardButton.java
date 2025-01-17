package io.chengine.message.keyboard;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class InlineKeyboardButton {

    private final String text;
    private final String url;
    private final String payload;
    private final boolean pay;

    private InlineKeyboardButton(
            String text,
            String url,
            String payload,
            boolean pay) {
        this.text = text;
        this.url = url;
        this.payload = payload;
        this.pay = pay;
    }

    public static InlineKeyboardButtonBuilder builder() {
        return new InlineKeyboardButtonBuilder();
    }

    public static class InlineKeyboardButtonBuilder {

        private String text;
        private String url;
        private String payload;
        private boolean pay;

        public InlineKeyboardButtonBuilder withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public InlineKeyboardButtonBuilder withUrl(Supplier<String> url) {
            this.url = url.get();
            return this;
        }


        public InlineKeyboardButtonBuilder withPayload(Supplier<String> payload) {
            this.payload = payload.get();
            return this;
        }

        public InlineKeyboardButtonBuilder isPay(BooleanSupplier pay) {
            this.pay = pay.getAsBoolean();
            return this;
        }

        public InlineKeyboardButton build() {
            return new InlineKeyboardButton(text, url, payload, pay);
        }
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String getPayload() {
        return payload;
    }

    public boolean isPay() {
        return pay;
    }
}
