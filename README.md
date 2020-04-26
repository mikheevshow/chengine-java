[![java][0]][1]

[0]: https://img.shields.io/badge/java-11-blue.svg?style=flat-square
[1]: https://openjdk.java.net/projects/jdk/11/

# Chengine

Chengine is a framework for creating chat bots.

## Quick Start

The central part of chengine is a ChengineHandlerContext class

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
