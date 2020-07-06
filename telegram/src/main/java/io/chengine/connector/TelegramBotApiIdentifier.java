package io.chengine.connector;

public class TelegramBotApiIdentifier implements BotApiIdentifier {

    @Override
    public String identifier() {
        return "telegram";
    }

}
