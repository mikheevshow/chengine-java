package io.chengine.springframework;

import io.chengine.annotation.CommandDescription;
import io.chengine.annotation.HandleCommand;
import io.chengine.connector.Message;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

@ComponentHandler
public class StartHandler {

    @HandleCommand("/test")
    public String startMethodHandler(User<?> user, Message<?> message) {
        return "Привет, хуило-" + user.username();
    }

    @HandleCommand("/help")
    @CommandDescription(description = {
            "RU : Помощь",
            "EN : Help"})
    public void sayHelp() {

    }
}
