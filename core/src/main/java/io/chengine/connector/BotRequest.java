package io.chengine.connector;

public class BotRequest {

	private BotApiIdentifier apiIdentifier;
	private boolean isCallback;
	private boolean isCommand;
	private ChatModel chat;
	private UserModel user;
	private Message message;
	private CallbackModel callback;

	public BotRequest(
			BotApiIdentifier apiIdentifier,
			boolean isCallback,
			boolean isCommand,
			ChatModel chat,
			UserModel user,
			Message message,
			CallbackModel callback) {

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

	public boolean isCallback() {
		return this.isCallback;
	}

	public boolean isCommand() {
		return this.isCommand;
	}

	public Message message() {
		return this.message;
	}

	public UserModel user() {
		return this.user;
	}

	public ChatModel chat() {
		return this.chat;
	}

	public CallbackModel callback() {
		return this.callback;
	}
}
