package io.chengine.connector;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class TelegramBotResponseConverter implements BotResponseConverter<BotApiMethod<?>> {

	public TelegramBotResponseConverter() {
	}

	@Override
	public BotApiMethod<?> convert(BotResponse response) {
		return null;
	}
}
