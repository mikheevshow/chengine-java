package io.chengine.connector;

public class BotResponse {

    private BotResponseStrategy responseStrategy;
    private boolean send = true;
    private Chat chat;
    private Message message;

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public BotResponseStrategy responseStrategy() {
        return responseStrategy;
    }

    public void setResponseStrategy(BotResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    public Chat chat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message message() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
