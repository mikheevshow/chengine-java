package io.chengine.springframework.boot.autoconfigure;

import io.chengine.TelegramLongPoolingBot;
import io.chengine.TelegramMessageProcessor;
import io.chengine.connector.TelegramBotResponseConverter;
import io.chengine.processor.ChengineMessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChengineTelegramClientAutoConfiguration {
    @Bean
    public TelegramMessageProcessor telegramMessageProcessor(ChengineMessageProcessor chengineMessageProcessor) {
        return new TelegramMessageProcessor(chengineMessageProcessor);
    }

    @Bean
    public TelegramBotResponseConverter telegramBotResponseConverter() {
        return new TelegramBotResponseConverter();
    }

    @Bean
    public TelegramLongPoolingBot telegramLongPoolingBot(
            TelegramMessageProcessor telegramMessageProcessor,
            TelegramBotResponseConverter telegramBotResponseConverter) {
        return new TelegramLongPoolingBot(telegramMessageProcessor,
                telegramBotResponseConverter,
                "1173254904:AAFGiJcEyUw8bRtZ-yOdwF4gby0cGNFLjQE", "just_the_best_bot");
    }
}
