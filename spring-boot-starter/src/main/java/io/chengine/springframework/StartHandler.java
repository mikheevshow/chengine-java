package io.chengine.springframework;

import io.chengine.annotation.CommandDescription;
import io.chengine.annotation.CommandParameter;
import io.chengine.annotation.HandleCommand;
import io.chengine.connector.Message;
import io.chengine.connector.User;
import io.chengine.springframework.stereotype.ComponentHandler;

import java.time.LocalDateTime;

@ComponentHandler
public class StartHandler {

    @HandleCommand("/test")
    @CommandDescription(description = {
            "RU : Команда для теста",
            "EN : The command for test"})
    public String startMethodHandler(User<?> user, Message<?> message) {
        return "Привет, хуило -" + user.username();
    }

    @HandleCommand("/myname")
    public String myName(User<?> user) {
        return "Меня зовут " + user.firstName() + " " + user.lastName();
    }

    @HandleCommand("/birhtdate/day#/month#/year#")
    public String myBirhtDate(
            @CommandParameter("day") Integer day,
            @CommandParameter("month") Integer month,
            @CommandParameter("year") Integer year) {

        return LocalDateTime.of(year, month, day, 0, 0).toString();
    }

    @HandleCommand("/help")
    @CommandDescription(description = {
            "RU : Помощь",
            "EN : Help"})
    public void sayHelp() {

    }
}
