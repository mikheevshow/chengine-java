package io.chengine.springframework;

import io.chengine.annotation.HandleCommand;
import io.chengine.connector.Message;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

@ComponentHandler
public class StartHandler {

    @HandleCommand("/test")
    public void startMethodHandler(User<?> user, Message<?> message) {
        System.out.println(user.firstName());
        System.out.println("message: " + message.command());
    }
}
