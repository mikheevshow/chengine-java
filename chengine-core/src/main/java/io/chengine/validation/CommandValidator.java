package io.chengine.validation;

import java.util.regex.Pattern;

public class CommandValidator {

	private final Pattern commandTemplatePattern = Pattern.compile("(/[a-z0-1]+#?)+");

	public boolean isNotCommand(String command) {
		return !command.startsWith("/");
	}

	public void validateCommandTemplate(String commandTemplate) throws CommandValidationException {

		if (isNotCommand(commandTemplate))
			throw new CommandValidationException("'" + commandTemplate + "' not a command. It shoud start from '/'.");

		if (!commandTemplatePattern.matcher(commandTemplate).matches())
			throw new CommandValidationException("'" + commandTemplate + "' doesn't agree with pattern \\{path}\\{param}#{value}.");
	}

	public void validateCommand(String command) throws CommandValidationException {

		if (isNotCommand(command))
			throw new CommandValidationException("'" + command + "' not a command. It shoud start from '/'.");

		String commandPattern = commandToTemplate(command);

		validateCommandTemplate(commandPattern);
	}

	private String commandToTemplate(String command) {

		StringBuilder stringBuilder = new StringBuilder();

		boolean append = false;

		for (char c : command.toCharArray()) {

			if (c == '/') {
				append = true;
			}

			if (c == '#') {
				append = false;
				stringBuilder.append(c);
			}

			if (append) {
				stringBuilder.append(c);
			}
		}

		return stringBuilder.toString();
	}
}
