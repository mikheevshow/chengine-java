[![java][0]][1] ![experimental do not use in prod][2]

[0]: https://img.shields.io/badge/java-11-blue.svg?style=flat-square
[1]: https://openjdk.java.net/projects/jdk/11/

[2]: https://img.shields.io/badge/development%20or%20experimental-do%20not%20use%20in%20prod-orange?style=flat-square

# Chengine

Chengine is a framework for creating chat bots.

## Quick Start

The central part of chengine is a ChengineHandlerContext class

### Manual handler registartion
- Firsly create a handler class
```java
import io.chengine.annotation.Handler;
import io.chengine.annotation.HandleCommand;

@Handler("/hello")
public class SomeHandler {

  @Command
  public void handleHelloCommand() {
    System.out.println("Hello World");
  }
}
```
- Register handler while create context
```java
import io.chengine.Configuration;

public class HelloWorld {
  public static void main(String[] args) {
   // Init context using manual handler registration
   ChengineContext context2 = new ChengineContext();
   context2.registerHandler(new SomeHandler());
  }
}

```

### Using Chengine in Spring Boot application

#### Yaml Configuration

#### Creation of command handlers

Basically chengine command handlers looks like Spring MVC rest controllers. You should annotate class by `@ComponentHandler` annotation, then
it will be found by spring context. If you want to make the same command prefix for all methods inside handler, just use `@CommandMapping` annotation.

```java

@ComponentHandler
@CommandMapping("/somecommand")
public class SomeHandler {

  private final SomeAnotherService someAnotherService;
  
  @Autowired
  public SomeHadler(final SomeAnotherService someAnotherService) {
    this.someAnotherService = someAnotherService;
  }
  
  @HandleCommand("/start")
  public Send sendGreeting() {
    return Send
                .message()
                .withText(() -> "Push button to say hello")
                .withInlineKeyBoard(k -> k
                        .addRow(r -> r
                              .addButton(b -> b
                                  .withText(() -> "Say hi!")
                                  .withData(() -> "/hello")
                              )
                              .addButton(b -> b
                                  .withText(() -> "Hide hello button")
                                  .withData(() -> "/hello/hide")
                              )
                        )
                )
                .done();
  }
  
  @HandleCommand("/hello")
  public Send sendGreetingMessage(User user) { // Current user injection
    return Send
                .message()
                .withText(() -> "Hello " + user.username())
                .done();
  }
  
  @HandleCommand("/hello/hide")
  public Edit hideHelloButton() { // Current message edit detection if inline keyboard button callback received
    return Edit
                .message()
                .removeInlineKeyboardButton(/`rowIndex/` 0, /`columnIndex/` 0)
                .done();
  }
  
  // Custom service using for fetch some data
  
  @HandleCommand("/winners")
  public Send getAllWinners(Chat chat) {
    return Send
               .message()
               .withText(() -> {
                  var winners = someService.getChatWinners(chat.id())
                  return "Our winners: " + winners.isEmpty() ? "nobody" : winners.toString();
               })
               .done();
  }

}
```
