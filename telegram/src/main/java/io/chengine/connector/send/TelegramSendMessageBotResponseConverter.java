package io.chengine.connector.send;

import io.chengine.connector.BotResponse;
import io.chengine.connector.BotResponseConverter;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramSendMessageBotResponseConverter implements BotResponseConverter<SendMessage> {

	@Override
	public SendMessage convert(BotResponse response) {

		var sendMessage = new SendMessage();
		sendMessage.setText(response.message().text());
		sendMessage.setChatId(response.chat().id());
		sendMessage.setParseMode(response.message().parseMode());
		sendMessage.setReplyMarkup(InlineKeyboardConverter.toTelegram(response.message().inlineKeyboard()));


		return sendMessage;
	}
}
