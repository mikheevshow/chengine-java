package io.chengine.connector;

import io.chengine.annotation.*;
import io.chengine.command.Command;
import io.chengine.command.DefaultCommandParser;
import io.chengine.command.HandleCommand;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.message.keyboard.InlineKeyboard;
import io.chengine.message.keyboard.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.Consumer;

import static io.chengine.message.keyboard.InlineKeyboardButton.InlineKeyboardButtonBuilder;

public class TelegramBotRequestConverter implements BotRequestConverter<Update> {

    @Override
    public BotRequestContext convert(Update update) {
        final DefaultBotRequestContext botRequestContext = new DefaultBotRequestContext();
        botRequestContext.setBotApiIdentifier(TelegramBotApiIdentifier.instance());
        addObjectsToContext(update, botRequestContext);
        setAnnotationToHandle(update, botRequestContext);
        setCommand(update, botRequestContext);

        return botRequestContext;
    }

    private void addObjectsToContext(Update update, DefaultBotRequestContext botRequestContext) {
        botRequestContext.add(Update.class, update);
        botRequestContext.add(Chat.class, getChat(update));
        botRequestContext.add(CallbackQuery.class, update.getCallbackQuery());
        botRequestContext.add(ChosenInlineQuery.class, update.getChosenInlineQuery());
        botRequestContext.add(InlineQuery.class, update.getInlineQuery());
        botRequestContext.add(Poll.class, update.getPoll());
        botRequestContext.add(ShippingQuery.class, update.getShippingQuery());
        botRequestContext.add(PreCheckoutQuery.class, update.getPreCheckoutQuery());
        botRequestContext.add(User.class, update.getMessage().getFrom());

        if (update.hasMessage()) {
            botRequestContext.add(Message.class, update.getMessage());
        }
        if (update.hasEditedMessage()) {
            botRequestContext.add(Message.class, update.getEditedMessage());
        }
        if (update.hasChannelPost()) {
            botRequestContext.add(Message.class, update.getChannelPost());
        }
        if (update.hasEditedChannelPost()) {
            botRequestContext.add(Message.class, update.getEditedMessage());
        }
    }

    private void setAnnotationToHandle(Update update, DefaultBotRequestContext botRequestContext) {

        // Callback query
        if (update.hasCallbackQuery()) {
            botRequestContext.setHandleAnnotation(HandleCommand.class);
            return;
        }

        // Command
        if (isCommand(update)) {
            botRequestContext.setHandleAnnotation(HandleCommand.class);
            return;
        }

        // Location
        if (update.getMessage() != null && update.getMessage().getLocation() != null) {
            botRequestContext.setHandleAnnotation(TelegramHandleLocation.class);
            return;
        }

        // Poll
        if (update.hasPoll()) {
            botRequestContext.setHandleAnnotation(TelegramHandlePoll.class);
            return;
        }

        // Poll answer
        if (update.hasPollAnswer()) {
            botRequestContext.setHandleAnnotation(TelegramHandlePollAnswer.class);
            return;
        }

        // Contact
        if (update.getMessage() != null && update.getMessage().getContact() != null) {
            botRequestContext.setHandleAnnotation(TelegramHandleContact.class);
            return;
        }

        if (update.hasInlineQuery()) {
            botRequestContext.setHandleAnnotation(TelegramHandleInlineQuery.class);
            return;
        }

        throw new RuntimeException("Unsupported request type. Request: " + update);
    }

    private void setCommand(Update update, DefaultBotRequestContext botRequestContext) {
        if (HandleCommand.class.equals(botRequestContext.shouldBeHandledByAnnotation())) {
            final Command command = extractCommand(update);
            if (command == null) {
                throw new NullPointerException("Command is null, but message should be handled like a command");
            }

            botRequestContext.setCommand(command);
        }
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

    private Chat getChat(Update update) {
        Chat chat;
        if (update.hasMessage()) {
            chat = update.getMessage().getChat();
        } else if (update.hasCallbackQuery()) {
            chat = update.getCallbackQuery().getMessage().getChat();
        } else {
            throw new RuntimeException("Can't find chat info");
        }

        return chat;
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
    private Command extractCommand(Update update) {
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
            for (org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton button : row) {
                Consumer<InlineKeyboardButtonBuilder> inlineKeyboardButtonBuilderConsumer = btn -> btn
                        .withText(button::getText)
                        .withPayload(button::getCallbackData)
                        .withUrl(button::getUrl);
                inlineKeyboardRow.addButton(inlineKeyboardButtonBuilderConsumer);
            }
            inlineKeyboardRows.add(inlineKeyboardRow.build());
        }

        var inlineKeyboard = new InlineKeyboard.InlineKeyboardBuilder();
        inlineKeyboard.addRows(() -> inlineKeyboardRows);

        return inlineKeyboard.build();
    }

}
