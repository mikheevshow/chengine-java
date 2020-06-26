package io.chengine.connector;

import org.telegram.telegrambots.meta.api.objects.User;

public class TelegramUser extends User implements io.chengine.connector.User<Integer> {

	@Override
	public Integer id() {
		return this.getId();
	}

	@Override
	public String username() {
		return this.getUserName();
	}

	@Override
	public String firstName() {
		return this.getFirstName();
	}

	@Override
	public String lastName() {
		return this.getLastName();
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
		return this.getBot();
	}

	@Override
	public String languageCode() {
		return this.getLanguageCode();
	}

	@Override
	public Boolean canJoinGroups() {
		return this.getCanJoinGroups();
	}

	@Override
	public Boolean canReadAllGroupMessages() {
		return this.getCanReadAllGroupMessages();
	}

	@Override
	public Boolean supportInlineQueries() {
		return this.getSupportInlineQueries();
	}
}
