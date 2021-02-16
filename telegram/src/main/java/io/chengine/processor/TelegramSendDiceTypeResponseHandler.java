package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendDice;
import io.chengine.method.HandlerMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramSendDiceTypeResponseHandler extends AbstractActionResponseMethodReturnedValueHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramSendDice.class;
    }

    @Override
    protected boolean isAllowToProcess(HandlerMethod handlerMethod, BotRequestContext request, BotResponseContext response) {
        return true;
    }

    @Override
    protected void process(
            HandlerMethod handlerMethod,
            ActionResponse returnedObject,
            BotRequestContext request,
            BotResponseContext response) {

        final TelegramSendDice telegramSendDice  = (TelegramSendDice) returnedObject;
        final DefaultBotResponseContext defaultBotResponseContext = (DefaultBotResponseContext) response;
        final Update update = (Update) request.get(Update.class);
        final Chat chat = update.getMessage().getChat();

        final SendDice sendDice = new SendDice();
        sendDice.setChatId(telegramSendDice.getChatId() != null ? telegramSendDice.getChatId() : chat.getId().toString());
        sendDice.setEmoji(telegramSendDice.getEmoji());

        defaultBotResponseContext.put(SendDice.class, sendDice);

    }
}
