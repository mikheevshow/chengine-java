package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.Send;
import io.chengine.method.Method;

import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

public class SendTypeResponseHandler extends AbstractResponseTypeHandler {

    @Override
    public Class<?> supports() {
        return Send.class;
    }

    @Override
    protected void process(Method method, Object object, BotRequest request, BotResponse response) {
        var send = (Send) object;
        var message = new Message(
                -1L,
                null,
                send.getText(),
                send.getParseMode(),
                send.getInlineKeyboard()
        );
        response.setResponseStrategy(SEND_MESSAGE);
        response.setMessage(message);
    }
}
