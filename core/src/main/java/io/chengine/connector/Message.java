package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.message.attachment.Attachment;
import io.chengine.message.keyboard.InlineKeyboard;

import java.util.List;
import java.util.Objects;

public class Message {

    private Long id;
    private Command command;
    private List<Attachment> attachments;
    private Location location;
    private String text;
    private String parseMode;
    private InlineKeyboard inlineKeyboard;

    public Message(Long id, Command command, String text, String parseMode, InlineKeyboard inlineKeyboard, List<Attachment> attachments) {
        this.id = id;
        this.command = command;
        this.text = text;
        this.parseMode = parseMode;
        this.inlineKeyboard = inlineKeyboard;
        this.attachments = attachments;
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

    public List<Attachment> attachments() {
        return this.attachments;
    }

    public Location location() {
        return location;
    }

    public String text() {
        return this.text;
    }

    public String parseMode() {
        return parseMode;
    }

    public InlineKeyboard inlineKeyboard() {
        return this.inlineKeyboard;
    }

}
