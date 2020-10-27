package io.chengine.connector;

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
		sendMessage.setText(response.getText());
		sendMessage.setChatId(response.getChatId());


		var chengineInlineKeyboard = response.getInlineKeyboard();
		if (chengineInlineKeyboard != null) {

			var telegramInlineKeyboardMarkup = new InlineKeyboardMarkup();

			var rows = new ArrayList<List<InlineKeyboardButton>>();

			chengineInlineKeyboard
					.getRows()
					.forEach(row -> {
						var telegramRow = new ArrayList<InlineKeyboardButton>();
						row.getButtons()
								.forEach(button -> {
									var telegramButton = new InlineKeyboardButton();
									telegramButton.setText(button.getText());
									telegramButton.setCallbackData(button.getData());
									telegramRow.add(telegramButton);
								});
						rows.add(telegramRow);
					});


			telegramInlineKeyboardMarkup.setKeyboard(rows);
			sendMessage.setReplyMarkup(telegramInlineKeyboardMarkup);
		}


		return sendMessage;
	}
}
