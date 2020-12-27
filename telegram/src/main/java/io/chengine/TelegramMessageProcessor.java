package io.chengine;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotRequestConverter;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramMessageProcessor implements MessageProcessorAware {

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

		if (botResponseContext.contains(SendMessage.class)) {
			final SendMessage sendMessage = (SendMessage) botResponseContext.get(SendMessage.class);
			telegramLongPoolingBot.execute(sendMessage);
		} else if (botResponseContext.contains(SendLocation.class)) {
			final SendLocation sendLocation = (SendLocation) botResponseContext.get(SendLocation.class);
			telegramLongPoolingBot.execute(sendLocation);
		} else if (botResponseContext.contains(SendPhoto.class)) {
			final SendPhoto sendPhoto = (SendPhoto) botResponseContext.get(SendPhoto.class);
			telegramLongPoolingBot.execute(sendPhoto);
		} else if (botResponseContext.contains(SendAudio.class)) {
			final SendAudio sendAudio = (SendAudio) botResponseContext.get(SendAudio.class);
			telegramLongPoolingBot.execute(sendAudio);
		} else if (botResponseContext.contains(SendVideo.class)) {
			final SendVideo sendVideo = (SendVideo) botResponseContext.get(SendVideo.class);
			telegramLongPoolingBot.execute(sendVideo);
		} else if (botResponseContext.contains(SendPoll.class)) {
			final SendPoll sendPoll = (SendPoll) botResponseContext.get(SendPoll.class);
			telegramLongPoolingBot.execute(sendPoll);
		} else if (botResponseContext.contains(StopPoll.class)) {
			final StopPoll stopPoll = (StopPoll) botResponseContext.get(StopPoll.class);
			telegramLongPoolingBot.execute(stopPoll);
		}
	}
}
