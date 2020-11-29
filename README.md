
<p align="center">
  <img width="350" height="250" src="https://i.ibb.co/tCD7fP6/1-2x.png">
</p>

# Chengine

> :warning: **UNSTABLE API**: Please don't use framework in production for now. Api is unstable yet and still changing.

[![Build Status](https://travis-ci.org/mikheevshow/chengine.svg?branch=develop)](https://travis-ci.org/mikheevshow/chengine)

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
