package io.chengine.connector;

public class Chat {

    private final String id;
    private final String type;
    private final String title;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String description;
    private final String inviteLink;


    public Chat(
            String id,
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

    public String id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String title() {
        return title;
    }

    public String username() {
        return username;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String description() {
        return description;
    }

    public String getInviteLink() {
        return inviteLink;
    }
}
