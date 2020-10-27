package io.chengine.connector;

public interface User<I> {

	I id();

	String username();

	String firstName();

	String lastName();

	String fatherName();

	String phone();

	Boolean isBot();

	String languageCode();

	Boolean canJoinGroups();

	Boolean canReadAllGroupMessages();

	Boolean supportInlineQueries();

}
