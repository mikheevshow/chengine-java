[![java][0]][1] ![experimental do not use in prod][2]

[0]: https://img.shields.io/badge/java-11-blue.svg?style=flat-square
[1]: https://openjdk.java.net/projects/jdk/11/

[2]: https://img.shields.io/badge/development%20or%20experimental-do%20not%20use%20in%20prod-orange?style=flat-square

# Chengine

Chengine is a framework for creating chat bots.

## Quick Start With Spring Boot

### Telegram Yaml Configuration

You can configure telegram long pooling bot by adding following lines below into your `application.yml` config file:

```yaml
chengine:
  telegram:
    token: 1173254904:AAABBBCCCEEEDDDFFFGGGIIIE
    username: just_some_bot_username
```

### Command Handler

``` java

import io.chengine.command.HandleCommand;
import io.chengine.message.Send;
import io.chengine.springframework.stereotype.HandlerComponent;

@HandlerComponent
public class Handlers {
  
  @HandleCommand("/hello")
  public Send hello() {
    return Send.messageWithText(() -> "Hello user!");
  }
  
}

```
