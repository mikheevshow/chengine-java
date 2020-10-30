package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.Edit;
import io.chengine.message.Send;
import io.chengine.method.Method;
import io.chengine.security.DefaultSecurityGuard;
import io.chengine.security.SecurityGuard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.chengine.connector.BotResponseStrategy.EDIT_MESSAGE;
import static io.chengine.connector.BotResponseStrategy.SEND_MESSAGE;

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
            var message = new Message(
                    -1L,
                    null,
                    send.getText(),
                    send.getInlineKeyboard()
            );
            botResponse.setResponseStrategy(SEND_MESSAGE);
            botResponse.setMessage(message);
        } else if (Edit.class.equals(objClass)) {
            log.debug("Called method with EDIT return type");
            if (securityGuard.methodCallingForEditMessage(method, botRequest)) {
                var edit = (Edit) object;
                if (botRequest.message() == null) {
                    // EDIT CURRENT MESSAGE
                    var message = new Message(
                            botRequest.message().id(),
                            null,
                            edit.getText(),
                            edit.getInlineKeyboard()
                    );
                    botResponse.setMessage(message);
                    botResponse.setResponseStrategy(EDIT_MESSAGE);
                } else {
                    // EDIT MESSAGE GIVEN BY USER
                }
            } else {
                log.debug("FAIL EDITING");
            }
        } else {
            var message = new Message(-1L, null, object.toString(), null);
            botResponse.setMessage(message);
        }

        botResponse.setChat(botRequest.chat());
    }
}