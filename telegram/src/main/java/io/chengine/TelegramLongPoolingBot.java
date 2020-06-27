package io.chengine;

import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private final MessageProcessor<Update> messageProcessor;
	private final String token;
	private final String username;

	public TelegramLongPoolingBot(
		TelegramMessageProcessor messageProcessor,
		String telegramToken,
		String telegramUsername
	) {

		this.messageProcessor = messageProcessor;
		this.token = telegramToken;
		this.username = telegramUsername;
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public void onUpdateReceived(Update update) {
		messageProcessor.process(update);
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public void onUpdatesReceived(List<Update> updates) {

	}
}
