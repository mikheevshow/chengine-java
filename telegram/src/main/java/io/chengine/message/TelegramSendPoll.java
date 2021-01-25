package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TelegramSendPoll extends TelegramAbstractSend {

    private String question;
    private final List<String> options = new ArrayList<>();
    private String explanation;
    private Integer correctOptionId;

    private Boolean allowMultiplyAnswers;
    private Boolean allowSendingWithoutReply;
    private Boolean disableNotification;
    private Boolean anonymous;
    private Boolean closed;

    public TelegramSendPoll toChatWithId(Supplier<String> chatId) {
        this.chatId = chatId.get();
        return this;
    }

    public TelegramSendPoll withQuestion(Supplier<String> question) {
        this.question = question.get();
        return this;
    }

    public TelegramSendPoll withExplanation(Supplier<String> explanation) {
        this.explanation = explanation.get();
        return this;
    }

    public TelegramSendPoll setCorrectOption(Supplier<Integer> correctOption) {
        this.correctOptionId = correctOption.get();
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

    public TelegramSendPoll allowMultiplyAnswers(BooleanSupplier allowMultiplyAnswers) {
        this.allowMultiplyAnswers = allowMultiplyAnswers.getAsBoolean();
        return this;
    }

    public TelegramSendPoll allowSendingWithoutReply(BooleanSupplier allowMultiplyAnswers) {
        this.allowMultiplyAnswers = allowMultiplyAnswers.getAsBoolean();
        return this;
    }

    public TelegramSendPoll disableNotification(BooleanSupplier disableNotification) {
        this.disableNotification = disableNotification.getAsBoolean();
        return this;
    }

    public TelegramSendPoll anonymous(BooleanSupplier anonymous) {
        this.anonymous = anonymous.getAsBoolean();
        return this;
    }

    public TelegramSendPoll closed(BooleanSupplier closed) {
        this.closed = closed.getAsBoolean();
        return this;
    }

    public TelegramSendPoll withInlineKeyboard(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        setInlineKeyboardInternal(inlineKeyboard);
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getExplanation() {
        return explanation;
    }

    public Integer getCorrectOptionId() {
        return correctOptionId;
    }

    public Boolean getAllowMultiplyAnswers() {
        return allowMultiplyAnswers;
    }

    public Boolean getAllowSendingWithoutReply() {
        return allowSendingWithoutReply;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public Boolean getClosed() {
        return closed;
    }

}
