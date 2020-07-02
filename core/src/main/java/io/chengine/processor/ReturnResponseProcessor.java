package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;

public class ReturnResponseProcessor implements ResponseResolver {
    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Object object) {
        if(object.getClass().equals(String.class)) {
            botResponse.setMessage(object.toString());
        }

        botResponse.setChatId(Long.parseLong(botRequest.chat().id().toString()));
    }
}