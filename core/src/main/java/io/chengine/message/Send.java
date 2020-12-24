package io.chengine.message;

import io.chengine.commons.Pair;
import io.chengine.connector.Location;
import io.chengine.message.attachment.Attachment;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Send implements ActionResponse {

    private Integer chatId;
    private String text;
    private String parseMode;
    private List<Attachment> attachments;
    private InlineKeyboard inlineKeyboard;
    private Keyboard keyboard;
    private Location location;

    // Constructors **************************************************************************************************

    private Send() {

    }

    private Send(
            final Integer chatId,
            final String text,
            final String parseMode,
            final List<Attachment> attachments,
            final InlineKeyboard inlineKeyboard,
            final Keyboard keyboard,
            final Location location) {

        this.chatId = chatId;
        this.text = text;
        this.parseMode = parseMode;
        this.attachments = attachments;
        this.inlineKeyboard = inlineKeyboard;
        this.keyboard = keyboard;
        this.location = location;
    }

    // Location ******************************************************************************************************

    public static SendLocation location() {
        return new SendLocation();
    }

    public static class SendLocation {

        private Integer chatId;
        private Location location;
        private InlineKeyboard inlineKeyboard;

        public SendLocation toChatWithId(Supplier<Integer> chatId) {
            if (chatId == null) {
                throw new NullPointerException("Chat id supplier can't be null");
            }
            this.chatId = chatId.get();
            return this;
        }

        public SendLocation withCoordinates(Supplier<Pair<Double, Double>> coordinates) {

            if (coordinates == null) {
                throw new NullPointerException("Coordinates supplier can't be null");
            }

            final Pair<Double, Double> coordinatesPair = coordinates.get();
            if (coordinatesPair == null) {
                throw new NullPointerException("Coordinates supplier returns null");
            }

            final Double longitude = coordinatesPair.getFirst();
            if (longitude == null) {
                throw new NullPointerException("Longitude can't be null");
            }

            final Double latitude = coordinatesPair.getSecond();
            if (latitude == null) {
                throw new NullPointerException("Latitude can't be null");
            }

            this.location = new Location(longitude, latitude);
            return this;
        }

        public SendLocation withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
            var inlineKeyboardBuilder = InlineKeyboard.builder();
            if (inlineKeyboard == null) {
                throw new NullPointerException("Inline keyboard consumer can't be null");
            }
            inlineKeyboard.accept(inlineKeyboardBuilder);
            this.inlineKeyboard = inlineKeyboardBuilder.build();
            return this;
        }

        public Send done() {
            return new Send(
                    chatId,
                    null,
                    null,
                    null,
                    inlineKeyboard,
                    null,
                    location
            );
        }
    }



    // Message *******************************************************************************************************

    public static SendMessage message() {
        return new SendMessage();
    }

    public static Send messageWithText(Supplier<String> text) {
        return Send
                .message()
                .withText(text)
                .done();
    }

    public static class SendMessage {

        private Integer chatId;
        private String text;
        private String parseMode;
        private List<Attachment> attachment = new ArrayList<>();
        private InlineKeyboard inlineKeyboard;
        private Keyboard keyboard;

        public SendMessage toChatWithId(Supplier<Integer> chatId) {
            if (chatId == null) {
                throw new NullPointerException("Chat id supplier can't be null");
            }
            this.chatId = chatId.get();
            return this;
        }

        public SendMessage withText(Supplier<String> text) {
            this.text = text.get();
            return this;
        }

        public SendMessage usingParseMode(Supplier<String> parseMode) {
            this.parseMode = parseMode.get();
            return this;
        }

        public SendMessage withAttachment(Supplier<Attachment> attachment) {
            if (attachment == null) {
                throw new NullPointerException("Attachment supplier can't be null");
            }
            this.attachment.add(attachment.get());
            return this;
        }

        public SendMessage withAttachments(Supplier<List<Attachment>> attachments) {
            if (attachment == null) {
                throw new NullPointerException("Attachment supplier can't be null");
            }
            final List<Attachment> attachmentsList = attachments.get();
            if (attachmentsList == null) {
                throw new NullPointerException("Attachments supplier returns null");
            }
            this.attachment.addAll(attachmentsList);
            return this;
        }

        public SendMessage withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
            var inlineKeyboardBuilder = InlineKeyboard.builder();
            inlineKeyboard.accept(inlineKeyboardBuilder);
            this.inlineKeyboard = inlineKeyboardBuilder.build();
            return this;
        }

        public SendMessage withKeyboard(Consumer<Keyboard.KeyboardBuilder> keyboard) {
            var keyboardBuilder = Keyboard.builder();
            keyboard.accept(keyboardBuilder);
            this.keyboard = keyboardBuilder.build();
            return this;
        }

        public Send done() {
            if (inlineKeyboard != null && keyboard != null) {
                throw new IllegalStateException("Inline keyboard and reply keyboard can't be non null at the same time");
            }

            return new Send(
                    chatId,
                    text,
                    parseMode,
                    attachment,
                    inlineKeyboard,
                    keyboard,
                    null
            );
        }
    }

    public Integer getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public String getParseMode() {
        return parseMode;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isLocation() {
        return location != null;
    }

    public boolean hasMedia() {
        return attachments != null && !attachments.isEmpty();
    }

    public boolean hasMediaGroup() {
        return hasMedia() && attachments.size() > 1;
    }

    @Override
    public String toString() {
        return "Send{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                ", parseMode='" + parseMode + '\'' +
                ", attachment=" + attachments +
                ", inlineKeyboard=" + inlineKeyboard +
                ", keyboard=" + keyboard +
                ", location=" + location +
                '}';
    }
}
