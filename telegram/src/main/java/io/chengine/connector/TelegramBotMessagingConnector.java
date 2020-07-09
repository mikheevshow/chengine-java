package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.CommandParser;
import io.chengine.command.CommandParsingException;
import io.chengine.command.DefaultCommandParser;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBotMessagingConnector implements BotMessagingConnector<Update> {

	private final CommandValidator commandValidator = DefaultCommandValidator.instance();
	private final CommandParser commandParser = DefaultCommandParser.instance();

	@Override
	public BotApiIdentifier identifier() {
		return TelegramBotApiIdentifier.instance();
	}

	@Override
	public boolean doesPlainTextSupport() {
		return true;
	}

	@Override
	public boolean containPlainText(Update update) {
		var message = update.getMessage();
		return message != null && message.hasText();
	}

	@Override
	public boolean doesCommandSupports() {
		return true;
	}

	@Override
	public boolean isCommand(Update update) {
		var message = update.getMessage();
		return message != null && (message.isCommand() || commandValidator.isCommand(message.getText()));
	}

	@Override
	public Command convert(String command) throws EmptyCommandException, CommandValidationException, CommandParsingException {
		commandValidator.validate(command);
		return commandParser.parse(command);
	}
}
