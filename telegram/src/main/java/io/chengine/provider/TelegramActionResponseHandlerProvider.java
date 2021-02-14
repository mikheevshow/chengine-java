package io.chengine.provider;

import io.chengine.processor.TelegramSendDiceTypeResponseHandler;
import io.chengine.processor.TelegramSendMessageTypeResponseHandler;
import io.chengine.processor.TelegramSendPhotoTypeResponseHandler;
import io.chengine.processor.AbstractActionResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class TelegramActionResponseHandlerProvider {

    private final List<AbstractActionResponseHandler> actionResponseHandlers = new ArrayList<>();

    public TelegramActionResponseHandlerProvider() {
        actionResponseHandlers.add(new TelegramSendMessageTypeResponseHandler());
        actionResponseHandlers.add(new TelegramSendPhotoTypeResponseHandler());
        actionResponseHandlers.add(new TelegramSendDiceTypeResponseHandler());
    }

    /**
     * {@inheritDoc}
     */
    public List<AbstractActionResponseHandler> provideAll() {
        return actionResponseHandlers;
    }
}
