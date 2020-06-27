package io.chengine;

import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.TelegramBotRequest;
import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramMessageProcessor implements MessageProcessor<Update, BotResponse> {

	private final MessageProcessor<BotRequest, BotResponse> messageProcessor;

	public TelegramMessageProcessor(MessageProcessor<BotRequest, BotResponse> messageProcessor) {
		this.messageProcessor = messageProcessor;
	}

	@Override
	public void process(Update request, BotResponse response) {
		try {
			var botRequest = new TelegramBotRequest(request);
			messageProcessor.process(botRequest, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
