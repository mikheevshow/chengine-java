package io.chengine.naturelang;

import io.chengine.command.Command;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SimpleTextCommandMapper implements TextCommandMapper {

    private final Map<String, Command> textCommandMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void mapAll(Command command, String... text) {
        if (text == null) {
            throw new NullPointerException("Text param can't be null");
        }

        for (final String t : text) {
            map(command, t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void map(Command command, String text) {
        if (text == null) {
            throw new NullPointerException("Text param can't be null");
        }

        if (command == null) {
            throw new NullPointerException("Command param can't be null");
        }

        if (textCommandMap.containsKey(text) && !textCommandMap.get(text).path().equals(command.path())) {
            final Command c = textCommandMap.get(text);
            throw new BindingAlreadyExists("Binding already exist. Text: " + text + "; Command: " + c);
        }

        textCommandMap.put(text, command);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Command get(String text) {
        return textCommandMap.getOrDefault(text, null);
    }
}
