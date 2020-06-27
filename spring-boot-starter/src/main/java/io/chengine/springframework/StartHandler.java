package io.chengine.springframework;

import io.chengine.annotation.Command;
import io.chengine.annotation.CommandParameter;
import io.chengine.connector.BotRequest;
import io.chengine.connector.Message;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

@ComponentHandler
public class StartHandler {

    @Command("/test")
    public void startMethodHandler(User<?> user, Message<?> message) {
        System.out.println(user.firstName());
        System.out.println("message: " + message);
    }
}
