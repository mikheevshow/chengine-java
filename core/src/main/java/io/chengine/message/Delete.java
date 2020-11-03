package io.chengine.message;

public class Delete {

    private final Integer chatId;
    private final Integer messageId;

    public Delete(Integer chatId, Integer messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }
}
