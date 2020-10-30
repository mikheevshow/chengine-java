package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.message.keyboard.InlineKeyboard;

import java.util.Objects;

public class Message {

    private long id;
    private Command command;
    private String text;
    private InlineKeyboard inlineKeyboard;

    public Message(long id, Command command, String text, InlineKeyboard inlineKeyboard) {
        this.id = id;
        this.command = command;
        this.text = text;
        this.inlineKeyboard = inlineKeyboard;
    }

    public long id() {
        return this.id;
    }

    public boolean containsCommand() {
        return Objects.nonNull(command);
    }

    public Command command() {
        return this.command;
    }

    public String text() {
        return this.text;
    }

    public InlineKeyboard inlineKeyboard() {
        return this.inlineKeyboard;
    }

}
