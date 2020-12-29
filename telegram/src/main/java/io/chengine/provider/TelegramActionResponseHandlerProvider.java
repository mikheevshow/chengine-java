package io.chengine.provider;

import io.chengine.processor.TelegramSendDiceTypeResponseHandler;
import io.chengine.processor.TelegramSendMessageTypeResponseHandler;
import io.chengine.processor.TelegramSendPhotoTypeResponseHandler;
import io.chengine.processor.response.AbstractActionResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class TelegramActionResponseHandlerProvider implements ActionResponseHandlerProvider{

    private final List<AbstractActionResponseHandler> actionResponseHandlers = new ArrayList<>();

    public TelegramActionResponseHandlerProvider() {
        actionResponseHandlers.add(new TelegramSendMessageTypeResponseHandler());
        actionResponseHandlers.add(new TelegramSendPhotoTypeResponseHandler());
        actionResponseHandlers.add(new TelegramSendDiceTypeResponseHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AbstractActionResponseHandler> provideAll() {
        return actionResponseHandlers;
    }
}
