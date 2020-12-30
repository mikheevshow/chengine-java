package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendDice;
import io.chengine.method.HandlerMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.objects.Chat;

public class TelegramSendDiceTypeResponseHandler extends AbstractActionResponseHandler {

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
        final Chat chat = (Chat) request.get(Chat.class);

        final SendDice sendDice = new SendDice();
        sendDice.setChatId(telegramSendDice.getChatId() != null ? telegramSendDice.getChatId() : chat.getId().toString());
        sendDice.setEmoji(telegramSendDice.getEmoji());

        defaultBotResponseContext.put(SendDice.class, sendDice);

    }
}
