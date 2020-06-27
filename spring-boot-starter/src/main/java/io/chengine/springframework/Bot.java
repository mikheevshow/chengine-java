package io.chengine.springframework;

import io.chengine.connector.BotRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        /*
         * 1) getUpdate
         * 2) BotRequest && BotResponse
         *      2.1) isCommand isMessage isImage (valid)
         *      2.2) if isCommand -> ChengineContext -> getHandler by Command
         *      2.3) We get Pair Handler, Method
         *      2.4) Get params annotation with @CommandParameter in this Method
         *      2.5) Save params (!ORDER!)
         *      2.6) invokeMethod handler with params and get Response
         *      2.7) Parse response and pack in BotResponse
         *      2.8) throwBack BotResponse
         */
    }

    @Override
    public String getBotUsername() {
        return "just_the_best_bot";
    }

    @Override
    public String getBotToken() {
        return "1173254904:AAFGiJcEyUw8bRtZ-yOdwF4gby0cGNFLjQE";
    }
}
