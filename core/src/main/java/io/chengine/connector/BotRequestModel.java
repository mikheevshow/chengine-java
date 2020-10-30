package io.chengine.connector;

public class BotRequestModel {

    private BotApiIdentifier apiIdentifier;
    private boolean isCallback;
    private boolean isCommand;
    private ChatModel chat;
    private UserModel user;
    private Message message;
    private CallbackModel callback;

    public BotApiIdentifier apiIdentifier() {
        return this.apiIdentifier;
    }

    public boolean isCallback() {
        return this.isCallback;
    }

    public boolean isCommand() {
        return this.isCommand;
    }

    public Message message() {
        return this.message;
    }

    public UserModel user() {
        return this.user;
    }

    public ChatModel chat() {
        return this.chat;
    }

    public CallbackModel callback() {
        return this.callback;
    }
}
