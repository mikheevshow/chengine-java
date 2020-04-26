Пожалуйста не комить в эту папку ничего, я тут работаю

Если вы используете SpringBoot и хотите воспользоваться всеми его преимуществами, в частности автоматическое управление 
зависимостями, используйте аннотацию @ComponentHandler:

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

  @Command("#id={}#info")
  public BotResponse someHandleMethod(@CommandParameter("id") final Long id, final BotRequest botRequest) {
    // Do some actions
  }
}
```

C другой стороны вы можете использовать комбинацию двух аннотаций

```java
import io.chengine.annotation.Handler;
import org.springframework.stereotype.Component;

@Component
@Handler
public class SomeHandler {
	
}
```
