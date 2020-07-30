package io.chengine.message;

import io.chengine.connector.Message;

public class Edit {

    public static EditBuilder message(Message<?> message) {
        return new EditBuilder();
    }

    public static class EditBuilder {

        private String text;

        public EditBuilder setText(String text) {
            return this;
        }

        public EditBuilder setReplyKeyboard() {
            return this;
        }

        public EditBuilder setMedia() {
            return this;
        }

        public Edit done() {
            return new Edit();
        }
    }

}
