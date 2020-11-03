package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.CommandParsingException;
import io.chengine.command.DefaultCommandParser;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.InlineKeyboardButton;
import io.chengine.message.keyboard.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TelegramBotRequestConverter implements BotRequestConverter<Update> {

    @Override
    public BotRequest convert(Update request) throws CommandParsingException, CommandValidationException, EmptyCommandException {
        return new BotRequest(
                TelegramBotApiIdentifier.instance(),
                isCallback(request),
                isCommand(request),
                convertChat(request),
                convertUser(request),
                convertMessage(request),
                convertCallback(request)
        );
    }

    private boolean isCallback(Update update) {
        return update.hasCallbackQuery();
    }

    private boolean isCommand(Update update) {
        var message = extractMessage(update);
        if (!message.hasText()) {
            return false;
        }
        var text = message.getText();
        var validator = DefaultCommandValidator.instance();
        return validator.isCommand(text);
    }

    private User convertUser(Update update) {
        var user = update.hasMessage() ? update.getMessage().getFrom() : update.getCallbackQuery().getFrom();
        return new User(
                user.getId(),
                user.getBot(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getLanguageCode(),
                user.getCanJoinGroups(),
                user.getCanReadAllGroupMessages(),
                user.getSupportInlineQueries()
        );
    }

    private Chat convertChat(Update update) {
        org.telegram.telegrambots.meta.api.objects.Chat chat;
        if (update.hasMessage()) {
            chat = update.getMessage().getChat();
        } else if (update.hasCallbackQuery()) {
            chat = update.getCallbackQuery().getMessage().getChat();
        } else {
            throw new RuntimeException("Can't find chat info");
        }
        return new Chat(
                chat.getId(),
                chat.getDescription(),
                chat.getTitle(),
                chat.getUserName(),
                chat.getFirstName(),
                chat.getLastName(),
                chat.getDescription(),
                chat.getInviteLink()
        );
    }

    private Message convertMessage(Update update) throws CommandParsingException, CommandValidationException, EmptyCommandException {
        org.telegram.telegrambots.meta.api.objects.Message message = extractMessage(update);
        return new Message(
                message.getMessageId().longValue(),
                extractCommand(update),
                message.getText(),
                null,
                convertMarkupToChengineInlineKeyboard(message.getReplyMarkup())
        );
    }

    private org.telegram.telegrambots.meta.api.objects.Message extractMessage(Update update) {
        if (update.hasMessage()) {
            return update.getMessage();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage();
        } else {
            throw new RuntimeException("Can't find message");
        }
    }

    @Nullable
    private Command extractCommand(Update update) throws CommandParsingException, CommandValidationException, EmptyCommandException {
        if (update.hasCallbackQuery()) {
            var text = update.getCallbackQuery().getData();
            var validator = DefaultCommandValidator.instance();
            if (validator.isCommand(text)) {
                var parser = DefaultCommandParser.instance();
                return parser.parse(text);
            }
        }

        if (update.hasMessage()) {
            var text = update.getMessage().getText();
            var validator = DefaultCommandValidator.instance();
            if (validator.isCommand(text)) {
                var parser = DefaultCommandParser.instance();
                return parser.parse(text);
            }
        }

        return null;
    }

    private InlineKeyboard convertMarkupToChengineInlineKeyboard(InlineKeyboardMarkup inlineKeyboardMarkup) {
        if (inlineKeyboardMarkup == null) {
            return null;
        }
        var rows = inlineKeyboardMarkup.getKeyboard();
        if (rows == null) {
            return null;
        }
        var inlineKeyboardRows = new ArrayList<InlineKeyboardRow>();
        for (var row : rows) {
            var inlineKeyboardRow = new InlineKeyboardRow.InlineKeyboardRowBuilder();
            row.stream().<Consumer<InlineKeyboardButton.InlineKeyboardButtonBuilder>>map(button -> btn -> btn
                    .withText(button::getText)
                    .withData(button::getCallbackData)
                    .withUrl(button::getUrl)).forEach(inlineKeyboardRow::addButton);
            inlineKeyboardRows.add(inlineKeyboardRow.build());
        }

        var inlineKeyboard = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.addRows(() -> inlineKeyboardRows);

        return inlineKeyboard.build();
    }

    @Nullable
    private Callback convertCallback(Update update) {
        if (!update.hasCallbackQuery()) {
            return null;
        }
        var callbackQuery = update.getCallbackQuery();
        return new Callback(
                callbackQuery.getId(),
                callbackQuery.getInlineMessageId(),
                callbackQuery.getChatInstance(),
                callbackQuery.getData()
        );
    }

}
