package io.chengine;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.connector.BotResponsePOJO;
import io.chengine.processor.MessageProcessor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;
import java.util.List;

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

	@SneakyThrows
	@Override
	public void onUpdateReceived(Update update) {
		log.info(update.toString());
		try {
			if (update.getCallbackQuery() == null) {
				var botResponse = new BotResponse();
				messageProcessor.process(update, botResponse);
				SendMessage sendMessage = new SendMessage();
				sendMessage.setText("botResponse.getMessage()");
				sendMessage.setChatId(update.getMessage().getChatId());
				var inline = new InlineKeyboardMarkup();

				var b = new InlineKeyboardButton();
				b.setText("hello");
				b.setCallbackData("/test");

				var c = new InlineKeyboardButton();
				c.setText("fff");
				c.setCallbackData("/test");
				inline.setKeyboard(List.of(List.of(b,c)));

				sendMessage.setReplyMarkup(inline);

				this.execute(sendMessage);
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
