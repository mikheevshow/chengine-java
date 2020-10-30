package io.chengine.connector;

public class ChatModel {

    private long id;
    private String type;
    private String title;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String inviteLink;


    public ChatModel(long id, String type, String title, String username, String firstName, String lastName, String description, String inviteLink) {
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
