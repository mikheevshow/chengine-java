package io.chengine.springframework.provider;

import io.chengine.pipeline.EventTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringTriggerProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public List<? extends EventTrigger> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(EventTrigger.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}