package io.chengine.connector;

import io.chengine.command.CommandParsingException;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.EmptyCommandException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBotRequest implements BotRequest{

    private User<Integer> user;
    private Message<?> message;
    private Chat<Long> chat;

    public TelegramBotRequest(Update update) throws CommandParsingException, CommandValidationException, EmptyCommandException {
        this.user = new TelegramUser(update.getMessage().getFrom());
        this.message = new TelegramMessage(update.getMessage());
    }

	@Override
    public BotClientIdentifier identifier() {
        return () -> "telegram";
    }

    @Override
    public Message<?> message() {
        return message;
    }

    @Override
    public User<?> user() {
        return user;
    }

    @Override
    public Chat<?> chat() {
        return chat;
    }
}
