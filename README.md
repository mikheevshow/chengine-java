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

Basically chengine command handlers looks like Spring MVC rest controllers. You should annotate class by @ComponentHanler annotation, then
it will be founded by spring context. If you want to make the same command prefix for all methods inside handler, just use @CommandMapping annotation.

```java

@ComponentHandler
@CommandMapping("/somecommand")
public class SomeHandler {

  private final SomeAnotherService someAnotherService;
  
  @Autowired
  public SomeHadler(final SomeAnotherService someAnotherService) {
    this.someAnotherService = someAnotherService;
  }

  @HandleCommand("/id#/info")
  public Edit editMessage(@CommandParameter("id") final Long id, final Message<?> message) {
    return Edit
                .message(message)
                .setText(someAnotherService.getInfoAboutProduct(id))
                .done();
  }
}
```
