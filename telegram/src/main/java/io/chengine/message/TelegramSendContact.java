package io.chengine.message;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class TelegramSendContact extends TelegramAbstractSend {

    private String vCard;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private Boolean allowSendingWithoutReply;
    private Boolean disableNotification;

    protected TelegramSendContact() {}

    public TelegramSendContact toChatWithId(@Nonnull Supplier<String> chatId) {
        toChatWithIdInternal(chatId);
        return this;
    }

    public TelegramSendContact withVCard(@Nonnull Supplier<String> vCard) {
        this.vCard = validateSupplier(vCard);
        return this;
    }

    public TelegramSendContact withReplyMessageId(@Nonnull Supplier<String> replyMessageId) {
        setReplyMessageIdInternal(replyMessageId);
        return this;
    }

    public TelegramSendContact withFirstName(@Nonnull Supplier<String> firstName) {
        this.firstName = validateSupplier(firstName);
        return this;
    }

    public TelegramSendContact withLastName(@Nonnull Supplier<String> lastName) {
        this.lastName = validateSupplier(lastName);
        return this;
    }

    public TelegramSendContact withPhoneNumber(@Nonnull Supplier<String> phoneNumber) {
        this.phoneNumber = validateSupplier(phoneNumber);
        return this;
    }

    public TelegramSendContact allowSendingWithoutReply(@Nonnull Supplier<Boolean> allowSendingWithoutReply) {
        this.allowSendingWithoutReply = validateSupplier(allowSendingWithoutReply);
        return this;
    }

    public TelegramSendContact disableNotification(@Nonnull Supplier<Boolean> disableNotification) {
        this.disableNotification = validateSupplier(disableNotification);
        return this;
    }

    public String getVCard() {
        return vCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getAllowSendingWithoutReply() {
        return allowSendingWithoutReply;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }
}
