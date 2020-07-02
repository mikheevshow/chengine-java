package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.CommandParsingException;
import io.chengine.command.DefaultCommandParser;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;

import java.util.Objects;

public class TelegramMessage implements Message<Integer> {

    private final Integer id;
    private final Command command;

    private TelegramMessage(Integer id, Command command) {
        this.id = id;
        this.command = command;
    }

    static public TelegramMessage create(org.telegram.telegrambots.meta.api.objects.Message message) throws EmptyCommandException, CommandValidationException, CommandParsingException {
        DefaultCommandValidator.getInstance().validate(message.getText());
        Command command = DefaultCommandParser.getInstance().parse(message.getText());

        return new TelegramMessage(message.getMessageId(), command);
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
}
