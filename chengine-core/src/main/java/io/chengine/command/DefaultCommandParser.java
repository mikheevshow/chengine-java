package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;

import java.util.LinkedHashMap;

public class DefaultCommandParser implements CommandParser {

	private CommandValidator commandValidator = new DefaultCommandValidator();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command parse(String command) throws CommandValidationException, EmptyCommandException, CommandParsingException  {

		commandValidator.validate(command);

		var commandPartHashMap = new LinkedHashMap<String, String>();

		CommandIterator
			.get(command)
			.forEachRemaining(part -> {

			});

		return new Command("/" + String.join("/", commandPartHashMap.keySet()), commandPartHashMap);
	}
}
