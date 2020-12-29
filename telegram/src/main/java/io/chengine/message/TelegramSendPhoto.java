package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramSendPhoto implements Send {

    private String chatId;
    private String caption;
    private String parseMode;
    private InputFile inputFile;
    private InlineKeyboard inlineKeyboard;

    public TelegramSendPhoto toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendPhoto withCaption(Supplier<String> caption) {
        this.caption = caption.get();
        return this;
    }

    public TelegramSendPhoto usingParseMode(Supplier<String> parseMode) {
        this.parseMode = parseMode.get();
        return this;
    }

    public TelegramSendPhoto attachFile(Supplier<InputFile> inputFile) {
        this.inputFile = inputFile.get();
        return this;
    }

    public TelegramSendPhoto withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        final InlineKeyboard.InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.accept(inlineKeyboardBuilder);
        this.inlineKeyboard = inlineKeyboardBuilder.build();
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public String getCaption() {
        return caption;
    }

    public String getParseMode() {
        return parseMode;
    }

    public InputFile getInputFile() {
        return inputFile;
    }

    public InlineKeyboard getInlineKeyboard() {
        return inlineKeyboard;
    }
}
