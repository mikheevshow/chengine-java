package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.connector.DefaultBotResponseContext;
import io.chengine.message.ActionResponse;
import io.chengine.message.TelegramSendPoll;
import io.chengine.method.HandlerMethod;
import io.chengine.util.InlineKeyboardConverter;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Chat;

public class TelegramSendPollTypeResponseHandler extends AbstractActionResponseHandler {

    @Override
    public Class<? extends ActionResponse> supports() {
        return TelegramSendPoll.class;
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

        final TelegramSendPoll telegramSendPoll = (TelegramSendPoll) returnedObject;
        final Chat chat = (Chat) request.get(Chat.class);

        final SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(telegramSendPoll.getChatId() != null ? telegramSendPoll.getChatId() : chat.getId().toString());
        sendPoll.setAllowMultipleAnswers(telegramSendPoll.getAllowMultiplyAnswers());
        sendPoll.setAllowSendingWithoutReply(telegramSendPoll.getAllowSendingWithoutReply());
        sendPoll.setDisableNotification(telegramSendPoll.getDisableNotification());
        sendPoll.setIsAnonymous(telegramSendPoll.getAnonymous());
        sendPoll.setIsClosed(telegramSendPoll.getClosed());
        sendPoll.setQuestion(telegramSendPoll.getQuestion());
        //sendPoll.setCloseDate(); TODO fill
        //sendPoll.setOpenPeriod();
        sendPoll.setExplanation(telegramSendPoll.getExplanation());
        sendPoll.setCorrectOptionId(telegramSendPoll.getCorrectOptionId());
        sendPoll.setReplyMarkup(InlineKeyboardConverter.toTelegram(telegramSendPoll.getInlineKeyboard()));


        ((DefaultBotResponseContext) response).setResponseObject(sendPoll);
    }
}
