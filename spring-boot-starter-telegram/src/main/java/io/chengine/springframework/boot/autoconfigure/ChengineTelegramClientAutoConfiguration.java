package io.chengine.springframework.boot.autoconfigure;

import io.chengine.TelegramLongPoolingBot;
import io.chengine.TelegramMessageProcessor;
import io.chengine.connector.BotRequestConverter;
import io.chengine.connector.TelegramBotRequestConverter;
import io.chengine.connector.send.TelegramSendMessageBotResponseConverter;
import io.chengine.processor.ChengineMessageProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
public class ChengineTelegramClientAutoConfiguration {

    @Value("${chengine.telegram.token}")
    private String token;

    @Value("${chengine.telegram.username}")
    private String username;

    @Bean
    public TelegramMessageProcessor telegramMessageProcessor(
            ChengineMessageProcessor chengineMessageProcessor,
            BotRequestConverter<Update> botRequestConverter) {
        return new TelegramMessageProcessor(chengineMessageProcessor, botRequestConverter);
    }

    @Bean
    public TelegramSendMessageBotResponseConverter telegramBotResponseConverter() {
        return new TelegramSendMessageBotResponseConverter();
    }

    @Bean
    public BotRequestConverter<Update> botRequestConverter() {
        return new TelegramBotRequestConverter();
    }

    @Bean
    public TelegramLongPoolingBot telegramLongPoolingBot(TelegramMessageProcessor telegramMessageProcessor, TelegramSendMessageBotResponseConverter telegramSendMessageBotResponseConverter) {
        return new TelegramLongPoolingBot(telegramMessageProcessor, telegramSendMessageBotResponseConverter, token, username);
    }
}
