package io.chengine.session;

public class SessionKey {

    private final String userId;
    private final String chatId;
    private final String botApiIdentifier;

    public SessionKey(String userId, String chatId, String botApiIdentifier) {
        this.userId = userId;
        this.chatId = chatId;
        this.botApiIdentifier = botApiIdentifier;
    }

    public String getUserId() {
        return userId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getBotApiIdentifier() {
        return botApiIdentifier;
    }
}
