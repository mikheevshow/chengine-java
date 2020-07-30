package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.InlineKeyboard.InlineKeyboardBuilder;
import io.chengine.message.keyboard.InlineKeyboardRow.InlineKeyboardRowBuilder;

public class Send {

    public static MessageBuilder message() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        private String text;
        private InlineKeyboard inlineKeyboard;

        public MessageBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public InlineKeyboardBuilder withInlineKeyboard() {
            return new InlineKeyboardBuilder();
        }

        public Send done() {
            return new Send();
        }
    }

}
