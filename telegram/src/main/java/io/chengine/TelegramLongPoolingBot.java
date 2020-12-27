package io.chengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private static final Logger log = LogManager.getLogger(TelegramLongPoolingBot.class);

	private final TelegramMessageProcessor messageProcessor;
	private final String token;
	private final String username;

	public TelegramLongPoolingBot(
		final String telegramToken,
		final String telegramUsername,
		final TelegramMessageProcessor telegramMessageProcessor) {

		super();
		this.token = telegramToken;
		this.username = telegramUsername;
		this.messageProcessor = telegramMessageProcessor;
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public void onUpdateReceived(Update update) {
		log.info("Received message: {}", update);
		try {
			messageProcessor.process(update, this);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
}
