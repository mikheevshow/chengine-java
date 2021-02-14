package io.chengine.session;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionKey)) return false;
        SessionKey that = (SessionKey) o;
        return userId.equals(that.userId) && chatId.equals(that.chatId) && botApiIdentifier.equals(that.botApiIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatId, botApiIdentifier);
    }
}
