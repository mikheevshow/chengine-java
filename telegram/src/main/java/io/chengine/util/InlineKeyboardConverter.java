package io.chengine.util;

import io.chengine.message.keyboard.InlineKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardConverter {

    private InlineKeyboardConverter() {}

    public static InlineKeyboardMarkup toTelegram(InlineKeyboard inlineKeyboard) {
        if (inlineKeyboard != null) {

            var telegramInlineKeyboardMarkup = new InlineKeyboardMarkup();

            var rows = new ArrayList<List<InlineKeyboardButton>>();

            inlineKeyboard
                    .getRows()
                    .forEach(row -> {
                        var telegramRow = new ArrayList<InlineKeyboardButton>();
                        row.getButtons()
                                .forEach(button -> {
                                    var telegramButton = new InlineKeyboardButton();
                                    telegramButton.setText(button.getText());
                                    telegramButton.setCallbackData(button.getData());
                                    telegramRow.add(telegramButton);
                                });
                        rows.add(telegramRow);
                    });


            telegramInlineKeyboardMarkup.setKeyboard(rows);

            return telegramInlineKeyboardMarkup;
        }

        return null;
    }

}
