package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;

import java.util.LinkedHashMap;

public class DefaultCommandParser implements CommandParser {

	private final CommandValidator commandValidator = new DefaultCommandValidator();
	private static volatile CommandParser COMMAND_PARSER = new DefaultCommandParser();

	public static CommandParser getInstance() {
		CommandParser commandParser = COMMAND_PARSER;
		if(COMMAND_PARSER == null) {
			synchronized (CommandValidator.class) {
				commandParser = COMMAND_PARSER;

				if(commandParser == null) {
					COMMAND_PARSER = commandParser = new DefaultCommandParser();
				}
			}
		}

		return commandParser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command parse(String command) throws CommandValidationException, EmptyCommandException, CommandParsingException  {

		commandValidator.validate(command);

		var commandPartHashMap = new LinkedHashMap<String, String>();
		var cmd = new StringBuilder();

		var iterator = CommandIterator.getInstance(command);

		while (iterator.hasNext()) {
			cmd.append("/");
			var part = iterator.next();
			if (part.contains("#")) {
				var param = part.substring(0, part.indexOf("#"));
				if (commandPartHashMap.containsKey(param)) {
					throw new CommandParsingException("Duplicate parameter " + param + " in " + command);
				}
				var value = part.substring(part.indexOf("#") + 1);
				commandPartHashMap.put(param, value);
				cmd.append(param).append("#");
			} else {
				cmd.append(part);
			}
		}

		return new Command(cmd.toString(), commandPartHashMap);
	}
}
