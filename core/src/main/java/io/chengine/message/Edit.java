package io.chengine.message;

import io.chengine.connector.Message;
import io.chengine.message.attachment.Attachment;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.InlineKeyboardButton;
import io.chengine.message.keyboard.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 */
public class Edit implements ActionResponse {

    private final Message messageForEdit;
    private final String text;
    private final String parseMode;
    private final Attachment attachment;
    private final InlineKeyboard inlineKeyboard;
    private final Keyboard keyboard;
    private final boolean removeInlineKeyboard;
    private final List<ButtonMarkup> editButtons;

    private Edit(
        final Message message,
        final String text,
        final String parseMode,
        final Attachment attachment,
        final InlineKeyboard inlineKeyboard,
        final Keyboard keyboard,
        final boolean removeInlineKeyboard,
        final List<ButtonMarkup> editButtons) {

        this.messageForEdit = message;
        this.text = text;
        this.parseMode = parseMode;
        this.attachment = attachment;
        this.inlineKeyboard = inlineKeyboard;
        this.keyboard = keyboard;
        this.removeInlineKeyboard = removeInlineKeyboard;
        this.editButtons = editButtons;
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
        private String parseMode;
        private Attachment attachment;
        private InlineKeyboard inlineKeyboard;
        private Keyboard keyboard;
        private boolean removeInlineKeyboard;
        private final List<ButtonMarkup> editButtons = new ArrayList<>();

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

        public EditBuilder changeButton(
                int rowIndex,
                int columnIndex,
                Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder> button) {

            var buttonBuilder = new InlineKeyboardButton.InlineKeyboardButtonBuilder();
            button.accept(buttonBuilder);
            var editButton = new ButtonMarkup(rowIndex, columnIndex, buttonBuilder.build());
            editButtons.add(editButton);
            return this;
        }


        public EditBuilder removeInlineKeyboard() {
            this.removeInlineKeyboard = true;
            return this;
        }

        public Edit done() {
            return new Edit(messageForEdit, text, parseMode, attachment, inlineKeyboard, keyboard, removeInlineKeyboard, editButtons);
        }

    }

    public Message getMessageForEdit() {
        return messageForEdit;
    }

    public String text() {
        return text;
    }

    public String parseMode() {
        return parseMode;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public boolean removeInlineKeyboard() {
        return removeInlineKeyboard;
    }

    public List<ButtonMarkup> getEditButtons() {
        return editButtons;
    }
}
