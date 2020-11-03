package io.chengine;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.processor.MessageProcessor;
import io.chengine.util.InlineKeyboardConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import static io.chengine.connector.BotResponseStrategy.EDIT_MESSAGE;
import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public class TelegramLongPoolingBot extends TelegramLongPollingBot {

	private static final Logger log = LogManager.getLogger(TelegramLongPoolingBot.class);

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
			var botResponse = new BotResponse();
			messageProcessor.process(update, botResponse);
			var responseStrategy = botResponse.getResponseStrategy();
			if (SEND_MESSAGE.equals(responseStrategy)) {
				this.execute(botResponseConverter.convert(botResponse));
			} else if (EDIT_MESSAGE.equals(responseStrategy)) {
				var edit = new EditMessageText()
						.setChatId(botResponse.getChat().getId())
						.setMessageId((int) botResponse.getMessage().id())
						.setText(botResponse.getMessage().text())
						.setReplyMarkup(InlineKeyboardConverter.toTelegram(botResponse.getMessage().inlineKeyboard()));
				this.execute(edit);
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
}
