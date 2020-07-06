package io.chengine.springframework;

import io.chengine.annotation.CommandParameter;
import io.chengine.annotation.HandleCommand;
import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.TelegramBotApiIdentifier;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;
import lombok.SneakyThrows;

@ComponentHandler
public class SomeHandler {

    @HandleCommand(value = "/news/from#/views#")
    public String someMethod(@CommandParameter("from") String from, @CommandParameter("views") String views, User<?> user) {
        return "News from " + from + " views " + views + " for " + user.username();
    }

    @SneakyThrows
    public static void main(String[] args) {
        var anno = SomeHandler.class.getMethod("someMethod", String.class, String.class, User.class).getAnnotation(HandleCommand.class);
        var api = anno.canBeHandledBy()[0];
        //System.out.println(api.is);
        //System.out.println(((BotApiIdentifier) api.getDeclaredConstructors()[0].newInstance()).identifier());
    }
}
