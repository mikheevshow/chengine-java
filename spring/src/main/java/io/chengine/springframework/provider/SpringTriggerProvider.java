package io.chengine.springframework.provider;

import io.chengine.pipeline.AbstractTrigger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringTriggerProvider implements TriggerProvider, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public List<? extends AbstractTrigger> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(AbstractTrigger.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}