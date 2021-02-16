package io.chengine.springframework.provider;

import io.chengine.processor.AbstractActionResponseMethodReturnedValueHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringResponseTypeHandlerProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public List<AbstractActionResponseMethodReturnedValueHandler> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(AbstractActionResponseMethodReturnedValueHandler.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
