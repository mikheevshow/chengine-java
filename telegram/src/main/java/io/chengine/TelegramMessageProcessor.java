package io.chengine;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotRequestConverter;
import io.chengine.connector.BotResponse;
import io.chengine.processor.MessageProcessor;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramMessageProcessor implements MessageProcessor<Update, BotResponse> {

	private final MessageProcessor<BotRequest, BotResponse> messageProcessor;
	private final BotRequestConverter<Update> botRequestConverter;

	public TelegramMessageProcessor(
			final MessageProcessor<BotRequest, BotResponse> messageProcessor,
			final BotRequestConverter<Update> botRequestConverter) {
		this.messageProcessor = messageProcessor;
		this.botRequestConverter = botRequestConverter;
	}

	@Override
	public void process(Update request, BotResponse response) throws Exception {
		var botRequest = botRequestConverter.convert(request);
		messageProcessor.process(botRequest, response);
	}

}
