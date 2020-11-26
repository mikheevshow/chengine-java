[Logo](https://ibb.co/PCg7pzy)

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

## Samples

## Guides

## License

   Copyright 2020 Ilya Mikheev, Andrey Borisov

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
