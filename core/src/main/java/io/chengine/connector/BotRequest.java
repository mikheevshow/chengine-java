package io.chengine.connector;

public class BotRequest {

	private final BotApiIdentifier apiIdentifier;
	private final Boolean isCommand;
	private final Boolean isCallback;
	private final Boolean isLocation;
	private final Boolean isAttachment;
	private final Chat chat;
	private final User user;
	private final Message message;
	private final Callback callback;

	/**
	 * Original api message, can be cast for specific type
	 */
	private final Object origin;

	public BotRequest(
			final Object origin,
			final BotApiIdentifier apiIdentifier,
			final Boolean isCallback,
			final Boolean isLocation,
			final Boolean isCommand,
			final Boolean isAttachment,
			final Chat chat,
			final User user,
			final Message message,
			final Callback callback) {

		this.origin = origin;
		this.apiIdentifier = apiIdentifier;
		this.isCallback = isCallback;
		this.isLocation = isLocation;
		this.isCommand = isCommand;
		this.isAttachment = isAttachment;
		this.chat = chat;
		this.user = user;
		this.message = message;
		this.callback = callback;
	}

	public Object origin() {
		return origin;
	}

	public BotApiIdentifier apiIdentifier() {
		return this.apiIdentifier;
	}

	public Boolean isCallback() {
		return this.isCallback;
	}

	public Boolean isLocation() {
		return isLocation;
	}

	public Boolean isCommand() {
		return this.isCommand;
	}

	public Boolean isAttachment() {
		return isAttachment;
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
