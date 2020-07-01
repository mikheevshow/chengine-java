package io.chengine.springframework;

import io.chengine.HandlerRegistry;
import io.chengine.annotation.HandleCommand;
import io.chengine.annotation.CommandParameter;
import io.chengine.command.Command;
import io.chengine.connector.*;
import io.chengine.springframework.stereotype.ComponentHandler;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpRequest;

@ComponentHandler(command = "/news")
@RequiredArgsConstructor
public class SomeHandler {

//    //private final HandlerRegistry handlerRegistry;
//
//    interface NativeClientFactory {
//
//        NativeClient get(BotClientIdentifier identifier);
//
//    }
//
//    NativeClientFactory nativeClientFactory = new DefaultNativeClientFactory();
//
//    interface NativeClient {
//
//
//    }
//
//    class TelegramNativeClient implements NativeClient {
//
//    }



    @HandleCommand(value = "/start#", canBeHandledFromBotsWithQualifier = "banger_shop")
    public void startNews(BotRequest request, HttpRequest) {
        System.out.println(request );
       // var telegramNativeClient = (TelegramNativeClient) nativeClientFactory.get(request.identifier());

    }



//    class DefaultNativeClientFactory implements NativeClientFactory {
//        @Override
//        public NativeClient get(BotClientIdentifier identifier) {
//            return null;
//        }
//    }
//
//
//    interface NativeClient {
//
//
//    }
//
//    class TelegramNativeClient implements NativeClient {
//
//    }

    @HandleCommand("/from#/views#")
    public void someMethod(@CommandParameter("from") String from, @CommandParameter("views") String views, User<?> user) {
        System.out.println("News from " + from + " views " + views + " for " + user.username());

        var command = Command.builder()
                .put("cart")
                .put("order")
                .put("product", "12")
                .build();


    }
}
