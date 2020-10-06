package io.chengine.connector;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramBotResponseConverter implements BotResponseConverter<BotApiMethod<?>> {

	public TelegramBotResponseConverter() {
	}

	@Override
	public BotApiMethod<?> convert(BotResponse response) {
		var sendMessage = new SendMessage();
		sendMessage.setText(response.getMessage());
		sendMessage.setChatId(response.getChatId());
		return sendMessage;
	}
}
