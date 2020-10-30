package io.chengine.connector;

public class Chat {

    private final long id;
    private final String type;
    private final String title;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String description;
    private final String inviteLink;


    public Chat(
            long id,
            String type,
            String title,
            String username,
            String firstName,
            String lastName,
            String description,
            String inviteLink) {

        this.id = id;
        this.type = type;
        this.title = title;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.inviteLink = inviteLink;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getInviteLink() {
        return inviteLink;
    }
}
