package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramSendPhoto extends TelegramAbstractSend {

    private String caption;
    private String parseMode;
    private InputFile inputFile;

    protected TelegramSendPhoto() {}

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
        setInlineKeyboardInternal(inlineKeyboard);
        return this;
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
}
