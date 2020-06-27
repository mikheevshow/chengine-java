package io.chengine;

import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.connector.TelegramBotRequest;
import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramMessageProcessor implements MessageProcessor<Update, BotApiMethod<?>> {

	private final CommandValidator commandValidator = new DefaultCommandValidator();

	@Override
	public void process(Update request, BotApiMethod<?> response) {
		var botRequest = new TelegramBotRequest(request);
	}

}
