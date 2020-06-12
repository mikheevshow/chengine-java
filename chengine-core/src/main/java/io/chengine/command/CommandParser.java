package io.chengine.command;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandParser {

	/**
	 * Parse a string representation of a command into {@link Command} object.
	 * It's important to call validate method before parse the command {@link io.chengine.validation.CommandValidator}
	 *
	 * @param command string representation of a command
	 * @return a command wrap
	 * @see Command
	 */
	public Command parseCommand(String command) throws CommandParsingException {

		StringBuilder commandSB = new StringBuilder();
		StringBuilder parameterSB = new StringBuilder();

		boolean fillCommand = false;
		boolean fillParameter = false;

		Map<String, String> mapParameterValue = new LinkedHashMap<>();

		var chars = command.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			if (chars[i] == '#') {
				fillCommand = false;
			}

			if (fillCommand) {
				commandSB.append(chars[i]);
			}

			if (chars[i] == '/') {
				fillParameter = false;
			}

			if (fillParameter) {
				parameterSB.append(chars[i]);
			}

			if ((i > 0 && chars[i] == '/') || (i + 1 == chars.length)) {
				fillParameter = false;
				mapParameterValue.put(commandSB.toString(), parameterSB.toString());
			}

			if (chars[i] == '/') {
				fillCommand = true;
				commandSB = new StringBuilder();
			}

			if (chars[i] == '#') {
				fillParameter = true;
				parameterSB = new StringBuilder();
			}
		}

		return new Command("/" + String.join("/", mapParameterValue.keySet()), mapParameterValue);
	}
}
