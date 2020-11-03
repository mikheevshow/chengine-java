package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.Edit;
import io.chengine.method.Method;
import io.chengine.security.DefaultSecurityGuard;
import io.chengine.security.SecurityGuard;

import static io.chengine.connector.BotResponseStrategy.EDIT_MESSAGE;

public class EditTypeResponseHandler extends AbstractResponseTypeHandler {

    private final SecurityGuard securityGuard = new DefaultSecurityGuard();

    @Override
    public Class<?> supports() {
        return Edit.class;
    }

    @Override
    protected void process(Method method, Object returnedObject, BotRequest request, BotResponse response) {
        if (securityGuard.methodCallingForEditMessage(method, request)) {
            var edit = (Edit) returnedObject;
            if (request.message() != null) {
                // EDIT CURRENT MESSAGE
                var message = new Message(
                        request.message().id(),
                        null,
                        edit.getText(),
                        null,
                        edit.getInlineKeyboard()
                );
                response.setMessage(message);
                response.setResponseStrategy(EDIT_MESSAGE);
            } else {
                // EDIT MESSAGE GIVEN BY USER
            }
        } else {
            log.info("FAIL EDITING");
        }
    }
}
