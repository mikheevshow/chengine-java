package io.chengine.session;

import java.util.Objects;

public class SessionKey {
    private final String api;
    private final Long userId;
    private final Long chatId;

    public SessionKey(String api, Long userId, Long chatId) {
        this.api = api;
        this.userId = userId;
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionKey sessionKey = (SessionKey) o;
        return Objects.equals(api, sessionKey.api) &&
            Objects.equals(userId, sessionKey.userId) &&
            Objects.equals(chatId, sessionKey.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(api, userId, chatId);
    }
}
