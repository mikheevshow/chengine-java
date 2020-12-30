package io.chengine.message;

import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TelegramSendMediaGroup implements Send {

    private String chatId;
    private final List<InputMedia> inputMedia = new ArrayList<>();

    public TelegramSendMediaGroup toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendMediaGroup attachFiles(Supplier<List<InputMedia>> inputMedia) {
        this.inputMedia.addAll(inputMedia.get());
        return this;
    }

    public TelegramSendMediaGroup attachFile(Supplier<InputMedia> inputMedia) {
        this.inputMedia.add(inputMedia.get());
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public List<InputMedia> getInputMedia() {
        return inputMedia;
    }
}
