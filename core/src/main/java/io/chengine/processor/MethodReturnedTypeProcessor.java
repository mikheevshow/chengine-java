package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.message.Edit;
import io.chengine.message.Send;
import io.chengine.method.Method;
import io.chengine.security.DefaultSecurityGuard;
import io.chengine.security.SecurityGuard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MethodReturnedTypeProcessor implements ResponseResolver {

    private static final Logger log = LogManager.getLogger(MethodReturnedTypeProcessor.class);

    final SecurityGuard securityGuard = new DefaultSecurityGuard();

    @Override
    public void resolve(BotRequest botRequest, BotResponse botResponse, Method method, Object object) {
        var objClass = object.getClass();

        if (objClass.equals(Void.TYPE)) {
            log.debug("Called method with VOID return type");
        } else if (Send.class.equals(objClass)) {
            log.debug("Called method with SEND return type");
            var send = (Send) object;
            botResponse.setText(send.getText());
            botResponse.setInlineKeyboard(send.getInlineKeyboard());
        } else if (Edit.class.equals(objClass)) {
            log.debug("Called method with EDIT return type");
            if (securityGuard.methodCallingForEditMessage(method, botRequest)) {
                if (botRequest.message() == null) {
                    // EDIT CURRENT MESSAGE
                } else {
                    // EDIT MESSAGE GIVEN BY USER
                }
            } else {
                log.debug("FAIL EDITING");
            }
        } else if (String.class.equals(objClass)) {
            botResponse.setText(object.toString());
        } else {
            log.debug("Called method with unknown: {} return type class", objClass);
        }

        botResponse.setChatId(Long.parseLong(botRequest.chat().id().toString()));
    }
}