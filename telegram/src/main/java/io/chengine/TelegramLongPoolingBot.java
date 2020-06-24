package io.chengine;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private final TelegramMessageProcessor messageProcessor;
	private final String token;
	private final String username;

	public TelegramLongPoolingBot(
		TelegramMessageProcessor messageProcessor,
		String telegramToken,
		String telegramUsername) {

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
		messageProcessor.onMessageReceived(update);
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public void onUpdatesReceived(List<Update> updates) {

	}
}
