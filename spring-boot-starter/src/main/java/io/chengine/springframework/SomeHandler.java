package io.chengine.springframework;

import io.chengine.annotation.Command;
import io.chengine.annotation.CommandDescription;
import io.chengine.annotation.CommandParameter;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

@ComponentHandler
public class SomeHandler {

    @Command("/news/from#/views#")
    public void someMethod(@CommandParameter("from") String from, @CommandParameter("views") String views, User<?> user) {
        System.out.println("News from " + from + " views " + views + " for " + user.username());
    }
}
