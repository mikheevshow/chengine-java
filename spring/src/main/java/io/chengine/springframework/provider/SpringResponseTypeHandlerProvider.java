package io.chengine.springframework.provider;

import io.chengine.processor.response.AbstractActionResponseHandler;
import io.chengine.provider.ResponseTypeHandlerProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringResponseTypeHandlerProvider implements ApplicationContextAware, ResponseTypeHandlerProvider {

    private ApplicationContext applicationContext;

    @Override
    public List<AbstractActionResponseHandler> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(AbstractActionResponseHandler.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
