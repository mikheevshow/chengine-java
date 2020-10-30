package io.chengine.connector;

public class BotResponse {

    private BotResponseStrategy responseStrategy;
    private Chat chat;
    private Message message;

    public BotResponseStrategy getResponseStrategy() {
        return responseStrategy;
    }

    public void setResponseStrategy(BotResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
