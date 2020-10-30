package io.chengine.connector;

public class User {

    private final long id;
    private final boolean isBot;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String languageCode;
    private final boolean canJoinGroups;
    private final Boolean canReadAllGroupMessages;
    private final Boolean supportInlineQueries;

    public User(
            long id,
            boolean isBot,
            String firstName,
            String lastName,
            String username,
            String languageCode,
            boolean canJoinGroups,
            boolean canReadAllGroupMessages,
            boolean supportInlineQueries) {
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

    public boolean isBot() {
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

    public boolean isCanJoinGroups() {
        return canJoinGroups;
    }

    public Boolean getCanReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    public Boolean getSupportInlineQueries() {
        return supportInlineQueries;
    }
}
