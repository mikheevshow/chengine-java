package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.connector.Message;
import io.chengine.message.Edit;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.method.HandlerMethod;
import io.chengine.security.DefaultSecurityGuard;
import io.chengine.security.SecurityGuard;

import static io.chengine.connector.BotResponseStrategy.EDIT_MESSAGE;

public final class EditTypeResponseHandler extends AbstractActionResponseHandler {

    private final SecurityGuard securityGuard = new DefaultSecurityGuard();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> supports() {
        return Edit.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(HandlerMethod handlerMethod, Object returnedObject, BotRequest request, BotResponse response) {
        if (securityGuard.callMethodToEditMessage(handlerMethod, request)) {
            var edit = (Edit) returnedObject;
            if (request.message() != null) {

                var messageId = request.message().id();
                var text = edit.text() == null ? request.message().text() : edit.text();
                var parseMode = edit.parseMode() == null ? request.message().parseMode() : edit.parseMode();
                var inlineKeyboard = mergeKeyboards(edit, request.message().inlineKeyboard());

                var message = new Message(messageId, null, text, parseMode, inlineKeyboard);

                response.setMessage(message);
                response.setResponseStrategy(EDIT_MESSAGE);

            }
        } else {
            log.error("Fail to edit message, method not support editing.");
        }
    }

    private InlineKeyboard mergeKeyboards(Edit edit, InlineKeyboard inlineKeyboardFromRequest) {

        if (edit.removeInlineKeyboard()) {
            log.warn("New markup or new buttons settings will be ignored, because remove keyboard flag detected");
            return null;
        }

        var inlineKeyboardFromEdit = edit.getInlineKeyboard();
        if (inlineKeyboardFromEdit != null && inlineKeyboardFromEdit.getRows() != null) {
            return inlineKeyboardFromEdit;
        }

        if (!edit.getEditButtons().isEmpty()) {
            edit.getEditButtons().forEach(buttonMarkup -> inlineKeyboardFromRequest.setButton(
                    buttonMarkup.getRowIndex(),
                    buttonMarkup.getColumnIndex(),
                    buttonMarkup.getButton())
            );

            return inlineKeyboardFromRequest;
        }

        return inlineKeyboardFromRequest;
    }
}
