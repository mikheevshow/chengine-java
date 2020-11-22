package io.chengine.springframework.provider;

import io.chengine.pipeline.EventTrigger;
import io.chengine.provider.TriggerProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringTriggerProvider implements TriggerProvider, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public List<? extends EventTrigger> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(EventTrigger.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}