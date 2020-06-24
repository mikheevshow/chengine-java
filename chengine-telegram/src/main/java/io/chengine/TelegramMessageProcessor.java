package io.chengine;

import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramMessageProcessor implements MessageProcessor<Update> {

	private final CommandValidator commandValidator = new DefaultCommandValidator();

	@Override
	public void onMessageReceived(Update message) {

	}

	@Override
	public boolean isCommand(Update message) {
		return message.getMessage().isCommand();
	}
}
