package io.chengine.connector;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBotRequest implements BotRequest{

    private User<Integer> user;
    private Message message;
    private Chat<Long> chat;

    public TelegramBotRequest(Update update) {

    }

    @Override
    public BotClientIdentifier identifier() {
        return () -> "telegram";
    }

    @Override
    public Message message() {
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
