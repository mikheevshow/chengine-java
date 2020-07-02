package io.chengine.connector;

public class TelegramChat implements Chat<Long> {

    private Long chatId;

    public TelegramChat(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public Long id() {
        return chatId;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String firstName() {
        return null;
    }

    @Override
    public String lastName() {
        return null;
    }

    @Override
    public String userName() {
        return null;
    }

    @Override
    public Boolean allMembersAreAdministrators() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String inviteLink() {
        return null;
    }

    @Override
    public Message<?> pinnedMessage() {
        return null;
    }

    @Override
    public String stickerSetName() {
        return null;
    }

    @Override
    public Boolean canSetStickerSet() {
        return null;
    }

    @Override
    public Integer slowModeDelay() {
        return null;
    }

	@Override
	public ChatPermissions permissions() {
		return null;
	}
}
