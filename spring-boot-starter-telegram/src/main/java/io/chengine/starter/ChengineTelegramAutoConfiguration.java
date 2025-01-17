package io.chengine.starter;

import io.chengine.TelegramLongPoolingBot;
import io.chengine.TelegramMessageProcessor;
import io.chengine.annotation.processor.TelegramSingleHandlerAnnotationProcessor;
import io.chengine.connector.BotRequestConverter;
import io.chengine.connector.TelegramBotRequestConverter;
import io.chengine.processor.*;
import io.chengine.session.SessionKeyExtractor;
import io.chengine.session.TelegramSessionKeyExtractor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Collections;
import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "telegramchengine", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ChengineTelegramAutoConfiguration {

    @Value("${chengine.telegram.token}")
    private String token;

    @Value("${chengine.telegram.username}")
    private String username;

    @Bean
    public SessionKeyExtractor sessionKeyExtractor() {
        return new TelegramSessionKeyExtractor();
    }

    @Bean
    public LongPollingBot longPollingBot(TelegramMessageProcessor telegramMessageProcessor) {
        return new TelegramLongPoolingBot(
                token,
                username,
                telegramMessageProcessor
        );
    }

    @Bean
    public TelegramMessageProcessor telegramMessageProcessor(BotRequestConverter<Update> botRequestConverter) {
        return new TelegramMessageProcessor(botRequestConverter);
    }

    @Bean
    public BotRequestConverter<Update> botRequestConverter() {
        return new TelegramBotRequestConverter();
    }

    @Bean
    public TelegramSendMediaGroupTypeResponseHandler telegramSendMediaGroupTypeResponseHandler() {
        return new TelegramSendMediaGroupTypeResponseHandler();
    }

    @Bean
    public TelegramSendPollTypeResponseHandler telegramSendPollTypeResponseHandler() {
        return new TelegramSendPollTypeResponseHandler();
    }

    @Bean
    public TelegramSendMessageTypeResponseHandler telegramSendTypeResponseHandler() {
        return new TelegramSendMessageTypeResponseHandler();
    }

    @Bean
    public TelegramSendPhotoTypeResponseHandler telegramSendPhotoTypeResponseHandler() {
        return new TelegramSendPhotoTypeResponseHandler();
    }

    @Bean
    public TelegramEditMessageCaptionTypeResponseHandler telegramEditTypeResponseHandler() {
        return new TelegramEditMessageCaptionTypeResponseHandler();
    }

    @Bean
    public TelegramDeleteTypeResponseHandler telegramDeleteTypeResponseHandler() {
        return new TelegramDeleteTypeResponseHandler();
    }

    @Bean
    public TelegramEditMessageTextTypeResponseHandler telegramEditMessageTextTypeResponseHandler() {
        return new TelegramEditMessageTextTypeResponseHandler();
    }

    @Bean
    public TelegramSingleHandlerAnnotationProcessor telegramSingleHandlerAnnotationProcessor() {
        return new TelegramSingleHandlerAnnotationProcessor();
    }

    @Bean
    @ConditionalOnMissingBean(TelegramBotsApi.class)
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public TelegramBotInitializer telegramBotInitializer(TelegramBotsApi telegramBotsApi, ObjectProvider<List<LongPollingBot>> longPollingBots) {
        return new TelegramBotInitializer(telegramBotsApi, longPollingBots.getIfAvailable(Collections::emptyList));
    }
}