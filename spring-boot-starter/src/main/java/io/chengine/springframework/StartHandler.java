package io.chengine.springframework;

import io.chengine.InlineKeyboard;
import io.chengine.Locale;
import io.chengine.annotation.HandleCommand;
import io.chengine.connector.Message;
import io.chengine.connector.TelegramBotMessagingConnector;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

import static io.chengine.Locale.*;

@ComponentHandler
public class StartHandler {

    @HandleCommand("/test")
    public void startMethodHandler(User<?> user, Message<?> message) {
        System.out.println(user.firstName());
        System.out.println("message: " + message);
    }

    @HandleCommand(value = "/karina#", canBeHandledFromBotsWithQualifier = {"banger_shop"}, canBeHandledBy = TelegramBotMessagingConnector.class)
    @InlineKeyboard(rows = {
            " Да | Нет ",
            " Наверное | Not | A lot ",
            " Может быть, я подумаю " },
            locale = RUSSIAN
    )
    public void karina() {
        System.out.println("Привет Карина!");
    }
}