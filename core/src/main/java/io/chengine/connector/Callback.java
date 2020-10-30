package io.chengine.connector;

import javax.annotation.Nullable;

public class Callback {

    private final String id;

    private final String inlineMessageId;

    private final String chatInstance;

    @Nullable
    private final String data;

    public Callback(String id, String inlineMessageId, String chatInstance, @Nullable String data) {
        this.id = id;
        this.inlineMessageId = inlineMessageId;
        this.chatInstance = chatInstance;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public String getChatInstance() {
        return chatInstance;
    }

    @Nullable
    public String getData() {
        return data;
    }
}
