package io.chengine.message;

import io.chengine.connector.Message;
import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 */
public class Edit {

    private final Message messageForEdit;
    private final String text;
    private final InlineKeyboard inlineKeyboard;

    private Edit(
        final Message message,
        final String text,
        final InlineKeyboard inlineKeyboard
    ) {
        this.messageForEdit = message;
        this.text = text;
        this.inlineKeyboard = inlineKeyboard;
    }

    /**
     * Edit current message from context. Those methods calls
     * when command received as a callback message from api.
     * <p>
     * <pre>
     * &#64;HandleCommand("/some-command")
     * public Edit handleSomeMessage() {
     *     return Edit
     *              .message()
     *              .setText(() -> "New text")
     *              .done();
     * }
     * </pre>
     *
     * @return edit builder
     */
    public static EditBuilder message() {
        return new EditBuilder();
    }

    /**
     * Edit concrete message
     *
     * The second variant to change current message shown below:
     *
     * <pre>
     * &#64;HandleCommand("/some-command")
     * public Edit handleSomeMessage(Message<?> msg) {
     *     return Edit
     *              .message(msg)
     *              .setText(() -> "New text")
     *              .done();
     * }
     * </pre>
     *
     * @param message - a message
     * @return edit builder
     */
    public static EditBuilder message(Message message) {
        return new EditBuilder(message);
    }

    public static class EditBuilder {

        private Message messageForEdit;
        private String text;
        private InlineKeyboard inlineKeyboard;

        public EditBuilder() {

        }

        public EditBuilder(Message message) {
            this.messageForEdit = message;
        }

        public EditBuilder setText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public EditBuilder setInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> keyboard) {
            var inlineKeyboardBuilder = new InlineKeyboard.InlineKeyboardBuilder();
            keyboard.accept(inlineKeyboardBuilder);
            this.inlineKeyboard = inlineKeyboardBuilder.build();
            return this;
        }

        public Edit done() {
            return new Edit(messageForEdit, text, inlineKeyboard);
        }

    }

    public Message getMessageForEdit() {
        return messageForEdit;
    }

    public String getText() {
        return text;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }
}
