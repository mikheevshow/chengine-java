package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.CommandParsingException;
import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.EmptyCommandException;

import javax.annotation.Nullable;

public interface BotMessagingConnector<IncomingMessage> {



	default boolean doesPlainTextSupport() {
		return true;
	}

	boolean containPlainText(IncomingMessage message);

	// ***************************** command methods *************************************

	default boolean doesCommandSupports() {
		return false;
	}

	default boolean isCommand(IncomingMessage message) {
		return false;
	}

	@Nullable
	default Command convert(String command) throws EmptyCommandException, CommandValidationException, CommandParsingException {
		return null;
	}

	// ***************************** media methods *************************************

	default boolean doesImagesReceivingSupports() {
		return false;
	}

	default boolean containsImages(IncomingMessage message) {
		return false;
	}

	default boolean doesVideoReceivingSupports() {
		return false;
	}

	default boolean containsVideos(IncomingMessage message) {
		return false;
	}

	default boolean doesAudioReceivingSupports() {
		return false;
	}

	default boolean containsAudios(IncomingMessage message) {
		return false;
	}


	// *********************** methods for sending  messages **************************

	default boolean doesImageSendingSupports() {
		return false;
	}

	default boolean doesVideoSendingSupports() {
		return false;
	}

	default boolean doesAudioSendingSupports() {
		return false;
	}

}
