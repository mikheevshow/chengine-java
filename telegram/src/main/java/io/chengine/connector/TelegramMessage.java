package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.CommandParsingException;
import io.chengine.command.DefaultCommandParser;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.EmptyCommandException;

import java.util.Objects;

public class TelegramMessage implements Message<Integer> {

    private final Integer id;
    private final Command command;

    public TelegramMessage(org.telegram.telegrambots.meta.api.objects.Message message)
            throws CommandParsingException, CommandValidationException, EmptyCommandException {
        this(
                message.getMessageId(),
                DefaultCommandParser.getInstance().parse(message.getText()) // todo
        );
    }

    private TelegramMessage(Integer id, Command command) {
        this.id = id;
        this.command = command;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public boolean isCommand() {
        return Objects.nonNull(command);
    }

    @Override
    public Command command() {
        return command;
    }

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "id=" + id +
                ", command=" + command +
                '}';
    }
}
