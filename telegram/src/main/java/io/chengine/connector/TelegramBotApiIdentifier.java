package io.chengine.connector;

public class TelegramBotApiIdentifier implements BotApiIdentifier {

    public static final String VALUE = "telegram";
    private static final BotApiIdentifier INSTANCE = new TelegramBotApiIdentifier();

    public static BotApiIdentifier instance() {
        return INSTANCE;
    }

    @Override
    public String identifier() {
        return VALUE;
    }

}
