package io.chengine;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private final MessageProcessor<Update, BotResponse> messageProcessor;
	private final BotResponseConverter<BotApiMethod<?>> botResponseConverter;
	private final String token;
	private final String username;

	public TelegramLongPoolingBot(
		final MessageProcessor<Update, BotResponse> messageProcessor,
		final BotResponseConverter<BotApiMethod<?>> botResponseConverter,
		final String telegramToken,
		final String telegramUsername
	) {

		this.messageProcessor = messageProcessor;
		this.botResponseConverter = botResponseConverter;
		this.token = telegramToken;
		this.username = telegramUsername;
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public void onUpdateReceived(Update update) {
		var botResponse = new BotResponse();
		messageProcessor.process(update, botResponse);
	}

	@Override
	public String getBotUsername() {
		return username;
	}
}
