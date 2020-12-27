package io.chengine.message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TelegramSendPoll implements Send {

    private String chatId;
    private String question;
    private final List<String> options = new ArrayList<>();

    public TelegramSendPoll toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendPoll withQuestion(Supplier<String> question) {
        this.question = question.get();
        return this;
    }

    public TelegramSendPoll addOption(Supplier<String> option) {
        this.options.add(option.get());
        return this;
    }

    public TelegramSendPoll addOptions(Supplier<List<String>> options) {
        this.options.addAll(options.get());
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }
}
