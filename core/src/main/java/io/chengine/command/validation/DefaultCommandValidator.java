package io.chengine.command.validation;

import io.chengine.command.Command;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultCommandValidator implements CommandValidator {

	private final Pattern commandTemplatePattern = Pattern.compile("(/[a-z0-9]+(#[a-z0-9]+)?)+");
	private final static long MAX_COMMAND_LENGTH = 64;
	private static volatile CommandValidator COMMAND_VALIDATOR;

	public static CommandValidator getInstance() {
		CommandValidator commandValidator = COMMAND_VALIDATOR;
		if(COMMAND_VALIDATOR == null) {
			synchronized (CommandValidator.class) {
				commandValidator = COMMAND_VALIDATOR;

				if(commandValidator == null) {
					COMMAND_VALIDATOR = commandValidator = new DefaultCommandValidator();
				}
			}
		}

		return commandValidator;
	}

	@Override
	public boolean isCommand(String s) {
		return s != null && s.trim().startsWith("/");
	}

	@Override
	public void validate(String s) throws CommandValidationException, EmptyCommandException {
		if (isCommand(s)) {
			var matcher = commandMatcherFor(s);
			if (s.trim().equals("/")) {
				throw new EmptyCommandException();
			}
			if (s.substring(1).length() > MAX_COMMAND_LENGTH) {
				throw new CommandValidationException("Command length more than 32 symbols.");
			}
			if (!matcher.matches()) {
				throw new CommandValidationException("Command doesn't match pattern " + commandTemplatePattern.pattern());
			}
		} else {
			if (s == null || s.isBlank()) {
				throw new EmptyCommandException();
			} else {
				throw new CommandValidationException(s + " is not a command");
			}
		}
	}

	@Override
	public Matcher commandMatcherFor(@Nonnull String command) {
		return commandTemplatePattern.matcher(Objects.requireNonNull(command, "Command can't be null"));
	}

}
