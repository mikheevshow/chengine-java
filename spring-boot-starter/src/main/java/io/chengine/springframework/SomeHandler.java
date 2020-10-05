package io.chengine.springframework;

import io.chengine.annotation.HandleCommand;
import io.chengine.command.Command;
import io.chengine.connector.Message;
import io.chengine.message.Edit;
import io.chengine.message.Send;
import io.chengine.springframework.stereotype.ComponentHandler;

import java.util.HashSet;

@ComponentHandler
public class SomeHandler {

    @HandleCommand("/start")
    public Send send() {

        return Send.message()
                .withText("Привет гребанный мир!")
                .withInlineKeyboard()
                .add(row -> row
                        .add(button -> button
                                .withText("привет"))
                        .add(button -> button
                                .withText("пока")))
                .add(row -> row
                        .add(button -> button
                                .withText("Здрасти")
                                .withData(Command.builder().put("some", "").build().path())))
                .then()
                .done();
    }

    @HandleCommand("/edit/left")
    public Edit edit(Message<?> message) {

//        new HashSet<String>().add();

        return Edit.message(message)
                .setText("Новая акция")
                .done();
    }
}
