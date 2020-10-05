package io.chengine.connector;

import io.chengine.command.CommandParsingException;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.EmptyCommandException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBotRequest implements BotRequest {

    private final boolean isCallback;
    private final User<Integer> user;
    private final Message<?> message;
    private final Chat<Long> chat;


    public TelegramBotRequest(Update update) throws CommandParsingException, CommandValidationException, EmptyCommandException {
        this.user = new TelegramUser(update.getMessage().getFrom());
        var callback = update.getCallbackQuery();
        this.isCallback = callback != null;
        this.message = callback != null ? TelegramMessage.create(callback.getMessage()) : TelegramMessage.create(update.getMessage());
        this.chat = new TelegramChat(update.getMessage().getChatId());
    }

	@Override
    public BotApiIdentifier identifier() {
        return TelegramBotApiIdentifier.instance();
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

    @Override
    public boolean isCallback() {
        return isCallback;
    }

    @Override
    public String toString() {
        return "TelegramBotRequest{" +
                "user=" + user +
                ", message=" + message +
                ", chat=" + chat +
                '}';
    }
}
