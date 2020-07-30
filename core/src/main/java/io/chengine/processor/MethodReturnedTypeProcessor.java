package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.message.Edit;
import io.chengine.message.Send;

public class MethodReturnedTypeProcessor implements ResponseResolver {
    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Object object) {
        var objClass = object.getClass();
        if (Send.class.equals(objClass)) {

        } else if (Edit.class.equals(objClass)) {

        } else if (String.class.equals(objClass)) {

        } else {
        }

        if(object.getClass().equals(String.class)) {
            botResponse.setMessage(object.toString());
        }

        botResponse.setChatId(Long.parseLong(botRequest.chat().id().toString()));
    }
}