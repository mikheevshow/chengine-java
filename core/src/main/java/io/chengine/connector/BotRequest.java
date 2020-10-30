package io.chengine.connector;

public class BotRequest {

	private final BotApiIdentifier apiIdentifier;
	private final Boolean isCallback;
	private final Boolean isCommand;
	private final Chat chat;
	private final User user;
	private final Message message;
	private final Callback callback;

	public BotRequest(
			BotApiIdentifier apiIdentifier,
			Boolean isCallback,
			Boolean isCommand,
			Chat chat,
			User user,
			Message message,
			Callback callback) {

		this.apiIdentifier = apiIdentifier;
		this.isCallback = isCallback;
		this.isCommand = isCommand;
		this.chat = chat;
		this.user = user;
		this.message = message;
		this.callback = callback;
	}

	public BotApiIdentifier apiIdentifier() {
		return this.apiIdentifier;
	}

	public Boolean isCallback() {
		return this.isCallback;
	}

	public Boolean isCommand() {
		return this.isCommand;
	}

	public Message message() {
		return this.message;
	}

	public User user() {
		return this.user;
	}

	public Chat chat() {
		return this.chat;
	}

	public Callback callback() {
		return this.callback;
	}
}
