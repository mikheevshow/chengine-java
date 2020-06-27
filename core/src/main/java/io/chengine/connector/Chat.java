package io.chengine.connector;

public interface Chat<Identifier> {

    Identifier id();

    String type();

    String title();

    String firstName();

    String lastName();

    String userName();

    Boolean allMembersAreAdministrators();
    
    String description();

    String inviteLink();

    Message<?> pinnedMessage();

    String stickerSetName();

    Boolean canSetStickerSet();

	ChatPermissions permissions();

    Integer slowModeDelay();

}
