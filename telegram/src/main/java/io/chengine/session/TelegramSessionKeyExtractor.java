package io.chengine.session;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequestContext;
import io.chengine.connector.TelegramBotApiIdentifier;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class TelegramSessionKeyExtractor implements SessionKeyExtractor {

    @Override
    public BotApiIdentifier support() {
        return TelegramBotApiIdentifier.instance();
    }

    @Override
    public SessionKey extractFrom(BotRequestContext requestContext) {
        final Update update = (Update) requestContext.get(Update.class);
        final User user = update.getMessage().getFrom();
        final Chat chat = update.getMessage().getChat();
        return new SessionKey(
                user.getId().toString(),
                chat.getId().toString(),
                support().identifier()
        );
    }
}
