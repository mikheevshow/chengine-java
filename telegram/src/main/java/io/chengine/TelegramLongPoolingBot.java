package io.chengine;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.connector.edit.EditResponseConverterFactory;
import io.chengine.connector.send.SendResponseConverterFactory;
import io.chengine.connector.send.TelegramSendMessageBotResponseConverter;
import io.chengine.processor.MessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static io.chengine.connector.BotResponseStrategy.EDIT_MESSAGE;
import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private static final Logger log = LogManager.getLogger(TelegramLongPoolingBot.class);

	private final MessageProcessor<Update, BotResponse> messageProcessor;
	private final BotResponseConverter<SendMessage> botResponseConverter;
	private final String token;
	private final String username;
	private final SendResponseConverterFactory sendResponseConverterFactory = new SendResponseConverterFactory();
	private final EditResponseConverterFactory editResponseConverterFactory = new EditResponseConverterFactory();

	public TelegramLongPoolingBot(

//		final MessageProcessor<Update, BotResponse> messageProcessor,
//		final BotResponseConverter<SendMessage> botResponseConverter,
		final String telegramToken,
		final String telegramUsername,
		final TelegramMessageProcessor telegramMessageProcessor,
		final TelegramSendMessageBotResponseConverter telegramSendMessageBotResponseConverter

	) {
		super();
//		this.messageProcessor = messageProcessor;
//		this.botResponseConverter = botResponseConverter;
		this.token = telegramToken;
		this.username = telegramUsername;
		this.messageProcessor = telegramMessageProcessor;
		this.botResponseConverter = telegramSendMessageBotResponseConverter;

	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public void onUpdateReceived(Update update) {
		log.info("Received message: {}", update);
		try {
			var botResponse = new BotResponse();
			messageProcessor.process(update, botResponse);
			var responseStrategy = botResponse.responseStrategy();
			if (SEND_MESSAGE.equals(responseStrategy)) {
				send(botResponse);
			} else if (EDIT_MESSAGE.equals(responseStrategy)) {
				edit(botResponse);
			} else {
				log.info("Unknown response strategy type");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	private void send(BotResponse response) throws TelegramApiException {
		var botResponseConverter = sendResponseConverterFactory.get(SendMessage.class);
		this.execute(botResponseConverter.convert(response));
	}

	private void edit(BotResponse response) throws TelegramApiException {
		var botResponseConverter = editResponseConverterFactory.get(EditMessageText.class);
		this.execute(botResponseConverter.convert(response));
	}
}
