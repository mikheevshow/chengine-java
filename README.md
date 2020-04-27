[![java][0]][1]

[0]: https://img.shields.io/badge/java-11-blue.svg?style=flat-square
[1]: https://openjdk.java.net/projects/jdk/11/

# Chengine

Chengine is a framework for creating chat bots.

## Quick Start

The central part of chengine is a ChengineHandlerContext class

### Manual handler registartion
- Firsly create a handler class
```java
import io.chengine.annotation.Handler;
import io.chengine.annotation.Command;

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

```java
import io.chengine.BotRequest;
import io.chengine.annotation.CommandParameter;
import io.chengine.springframework.stereotype.ComponentHandler;
import org.springframework.beans.factory.annotation.Autowired;

@ComponentHandler("/command")
public class SomeHandler {

  private final SomeAnotherService someAnotherService;
  
  @Autowired
  public SomeHadler(final SomeAnotherService someAnotherService) {
    this.someAnotherService = someAnotherService;
  }

  @Command("/id#/info")
  public BotResponse someHandleMethod(@CommandParameter("id") final Long id, final BotRequest botRequest) {
    // Do some actions
  }
}
```
