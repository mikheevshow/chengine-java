package io.chengine.connector;

import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TelegramBotResponseConverter implements BotResponseConverter<BotApiMethod<?>> {

	@Override
	public BotApiMethod<?> convert(BotResponse response) {

		var sendMessage = new SendMessage();
		sendMessage.setText(response.getMessage().text());
		sendMessage.setChatId(response.getChat().getId());


		var chengineInlineKeyboard = response.getMessage().inlineKeyboard();
		var telegramKeyboard = InlineKeyboardConverter.toTelegram(chengineInlineKeyboard);
		sendMessage.setReplyMarkup(telegramKeyboard);


		return sendMessage;
	}
}
