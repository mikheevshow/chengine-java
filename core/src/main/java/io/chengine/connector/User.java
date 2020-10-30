package io.chengine.connector;

public class User {

    private final long id;
    private final Boolean isBot;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String languageCode;
    private final Boolean canJoinGroups;
    private final Boolean canReadAllGroupMessages;
    private final Boolean supportInlineQueries;

    public User(
            long id,
            Boolean isBot,
            String firstName,
            String lastName,
            String username,
            String languageCode,
            Boolean canJoinGroups,
            Boolean canReadAllGroupMessages,
            Boolean supportInlineQueries) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
        this.canJoinGroups = canJoinGroups;
        this.canReadAllGroupMessages = canReadAllGroupMessages;
        this.supportInlineQueries = supportInlineQueries;
    }


    public long getId() {
        return id;
    }

    public Boolean isBot() {
        return isBot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public Boolean isCanJoinGroups() {
        return canJoinGroups;
    }

    public Boolean getCanReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    public Boolean getSupportInlineQueries() {
        return supportInlineQueries;
    }
}
