package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.message.Edit;
import io.chengine.message.Send;

public class MethodReturnedTypeProcessor implements ResponseResolver {
    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Object object) {
        var objClass = object.getClass();

        if (objClass.equals(Void.TYPE)) {

        } else if (Send.class.equals(objClass)) {
            var send = (Send) object;
            botResponse.setText(send.getText());
            botResponse.setInlineKeyboard(send.getInlineKeyboard());
        } else if (Edit.class.equals(objClass)) {

        } else if (String.class.equals(objClass)) {
            botResponse.setText(object.toString());
        } else {

        }

        botResponse.setChatId(Long.parseLong(botRequest.chat().id().toString()));
    }
}