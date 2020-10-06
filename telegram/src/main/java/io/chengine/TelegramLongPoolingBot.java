package io.chengine;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.processor.MessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private final static Logger log = LogManager.getLogger(TelegramLongPoolingBot.class);

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
		log.debug("Received message: {}", update);
		try {
			if (update.getCallbackQuery() == null) {
				var botResponse = new BotResponse();
				messageProcessor.process(update, botResponse);
				this.execute(botResponseConverter.convert(botResponse));
			} else  {
				this.execute(new EditMessageText()
						.setText("Huita")
						.setMessageId(update.getCallbackQuery().getMessage().getMessageId())
						.setChatId(update.getCallbackQuery().getMessage().getChatId())
				);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Override
	public String getBotUsername() {
		return username;
	}
}
