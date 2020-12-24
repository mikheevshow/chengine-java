package io.chengine;

import io.chengine.connector.Message;
import io.chengine.message.Delete;
import io.chengine.message.Edit;
import io.chengine.sender.MessageSender;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

public class TelegramMessageSender implements MessageSender {

    private final LongPollingBot longPollingBot;

    public TelegramMessageSender(LongPollingBot longPollingBot) {
        this.longPollingBot = longPollingBot;
    }

    @Override
    public void send(Message message) {

    }

    @Override
    public void edit(Edit message) {

    }

    @Override
    public void delete(Delete message) {

    }
}
