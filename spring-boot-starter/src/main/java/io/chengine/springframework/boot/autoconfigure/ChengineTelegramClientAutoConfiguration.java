package io.chengine.springframework.boot.autoconfigure;

import io.chengine.TelegramLongPoolingBot;
import io.chengine.TelegramMessageProcessor;
import io.chengine.connector.TelegramBotResponseConverter;
import io.chengine.processor.ChengineMessageProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChengineTelegramClientAutoConfiguration {

    @Value(" ${chengine.telegram.token} ")
    private String token;

    @Value(" ${chengine.telegram.username} ")
    private String username;

    @Bean
    public TelegramMessageProcessor telegramMessageProcessor(ChengineMessageProcessor chengineMessageProcessor) {
        return new TelegramMessageProcessor(chengineMessageProcessor);
    }

    @Bean
    public TelegramBotResponseConverter telegramBotResponseConverter() {
        return new TelegramBotResponseConverter();
    }

    @Bean
    public TelegramLongPoolingBot telegramLongPoolingBot(TelegramMessageProcessor telegramMessageProcessor, TelegramBotResponseConverter telegramBotResponseConverter) {
        return new TelegramLongPoolingBot(telegramMessageProcessor, telegramBotResponseConverter, token, username);
    }
}
