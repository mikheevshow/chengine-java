package io.chengine;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotRequestConverter;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.processor.MessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramMessageProcessor implements MessageProcessorAware {

	private static final Logger log = LogManager.getLogger(TelegramMessageProcessor.class);

	private MessageProcessor<BotRequestContext, BotResponseContext> messageProcessor;
	private final BotRequestConverter<Update> botRequestConverter;

	public TelegramMessageProcessor(final BotRequestConverter<Update> botRequestConverter) {
		this.botRequestConverter = botRequestConverter;
	}

	public void process(Update update, TelegramLongPoolingBot telegramLongPoolingBot) throws Exception {
		final BotRequestContext botRequest = botRequestConverter.convert(update);
		final BotResponseContext botResponseContext = new DefaultBotResponseContext();
		messageProcessor.process(botRequest, botResponseContext);

		execute(botResponseContext, telegramLongPoolingBot);
	}

	@Override
	public void setMessageProcessor(MessageProcessor<BotRequestContext, BotResponseContext> messageProcessor) {
		this.messageProcessor = messageProcessor;
	}

	private void execute(
			final BotResponseContext botResponseContext,
			final TelegramLongPoolingBot telegramLongPoolingBot) throws TelegramApiException {

		if (BotApiMethod.class.isAssignableFrom(botResponseContext.responseClass())) {
			telegramLongPoolingBot.execute((BotApiMethod<?>) botResponseContext.getResponseObject());
		} else if (SendPhoto.class.equals(botResponseContext.responseClass())) {
			telegramLongPoolingBot.execute((SendPhoto) botResponseContext.getResponseObject());
		} else if (SendMediaGroup.class.equals(botResponseContext.responseClass())) {
			telegramLongPoolingBot.execute((SendMediaGroup) botResponseContext.getResponseObject());
		} else if (SendPoll.class.equals(botResponseContext.responseClass())) {
			telegramLongPoolingBot.execute((SendPoll) botResponseContext.getResponseObject());
		} else {
			log.error("Response object is not assignable from BotApiMethod class. This is may a bug, " +
					"please contact support mikheev.show@gmail.com also attach steps to reproduce the problem");
		}
	}
}
