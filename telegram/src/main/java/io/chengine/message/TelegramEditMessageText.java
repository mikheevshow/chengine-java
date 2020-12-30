package io.chengine.message;

public class TelegramEditMessageText implements Edit {

    private Integer chatId;
    private Integer messageId;
    private String text;
    private String parseMode;


    public Integer getChatId() {
        return chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public String getParseMode() {
        return parseMode;
    }
}
