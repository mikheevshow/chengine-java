package io.chengine.connector;

public class TelegramBotApiIdentifier implements BotApiIdentifier {

    private static final BotApiIdentifier INSTANCE = new TelegramBotApiIdentifier();

    public static BotApiIdentifier instance() {
        return INSTANCE;
    }

    @Override
    public String identifier() {
        return "telegram";
    }

}
