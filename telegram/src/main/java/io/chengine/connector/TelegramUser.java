package io.chengine.connector;

import org.telegram.telegrambots.meta.api.objects.User;

public class TelegramUser implements io.chengine.connector.User<Integer> {

    private final Integer id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Boolean isBot;
    private final String languageCode;
    private final Boolean canJoinGroups;
    private final Boolean canReadAllGroupMessages;
    private final Boolean supportInlineQueries;

    public TelegramUser(User user) {
        this(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getBot(),
                user.getLanguageCode(),
                user.getCanJoinGroups(),
                user.getCanReadAllGroupMessages(),
                user.getSupportInlineQueries()
        );
    }

    private TelegramUser(
            Integer id,
            String username,
            String firstName,
            String lastName,
            Boolean isBot,
            String languageCode,
            Boolean canJoinGroups,
            Boolean canReadAllGroupMessages,
            Boolean supportInlineQueries
    ) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isBot = isBot;
        this.languageCode = languageCode;
        this.canJoinGroups = canJoinGroups;
        this.canReadAllGroupMessages = canReadAllGroupMessages;
        this.supportInlineQueries = supportInlineQueries;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String fatherName() {
        return null;
    }

    @Override
    public String phone() {
        return null;
    }

    @Override
    public Boolean isBot() {
        return isBot;
    }

    @Override
    public String languageCode() {
        return languageCode;
    }

    @Override
    public Boolean canJoinGroups() {
        return canJoinGroups;
    }

    @Override
    public Boolean canReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    @Override
    public Boolean supportInlineQueries() {
        return supportInlineQueries;
    }

    @Override
    public String toString() {
        return "TelegramUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isBot=" + isBot +
                ", languageCode='" + languageCode + '\'' +
                ", canJoinGroups=" + canJoinGroups +
                ", canReadAllGroupMessages=" + canReadAllGroupMessages +
                ", supportInlineQueries=" + supportInlineQueries +
                '}';
    }
}
