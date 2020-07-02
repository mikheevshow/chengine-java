package io.chengine.springframework;

import io.chengine.annotation.HandleCommand;
import io.chengine.annotation.CommandParameter;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

@ComponentHandler
public class SomeHandler {

    @HandleCommand("/news/from#/views#")
    public String someMethod(@CommandParameter("from") String from, @CommandParameter("views") String views, User<?> user) {
        return "News from " + from + " views " + views + " for " + user.username();
    }
}
