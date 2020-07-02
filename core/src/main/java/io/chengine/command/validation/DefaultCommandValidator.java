package io.chengine.command.validation;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultCommandValidator implements CommandValidator {

	private final Pattern commandTemplatePattern = Pattern.compile("(/[a-z0-9]+(#[a-z0-9]+)?)+");
	private static CommandValidator DEFAULT_COMMAND_VALIDATOR;

	public static synchronized CommandValidator getInstance() {
		if(DEFAULT_COMMAND_VALIDATOR == null) {
			DEFAULT_COMMAND_VALIDATOR = new DefaultCommandValidator();
		}

		return DEFAULT_COMMAND_VALIDATOR;
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
			if (s.substring(1).length() > 32) {
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
