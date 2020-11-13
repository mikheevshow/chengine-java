package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.Send;
import io.chengine.method.Method;

import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public final class SendTypeResponseHandler extends AbstractResponseTypeHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> supports() {
        return Send.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(Method method, Object object, BotRequest request, BotResponse response) {
        var send = (Send) object;
        var message = new Message(
                null,
                null,
                send.text(),
                send.parseMode(),
                send.inlineKeyboard()
        );
        response.setResponseStrategy(SEND_MESSAGE);
        response.setMessage(message);
    }
}
