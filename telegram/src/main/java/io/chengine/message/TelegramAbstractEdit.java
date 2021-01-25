package io.chengine.message;

import io.chengine.message.keyboard.InlineKeyboard;

import java.util.function.Consumer;

public class TelegramAbstractEdit  implements Edit {

    protected String chatId;
    protected String messageId;

    protected InlineKeyboard inlineKeyboard;


    protected void setInlineKeyboardInternal(Consumer<InlineKeyboard.InlineKeyboardBuilder> inlineKeyboard) {
        final InlineKeyboard.InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.accept(inlineKeyboardBuilder);
        this.inlineKeyboard = inlineKeyboardBuilder.build();
    }

}
