package io.chengine.springframework.provider;

import io.chengine.commons.RequestTypeConverter;
import io.chengine.provider.RequestTypeConverterProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringRequestTypeConverterProvider implements ApplicationContextAware, RequestTypeConverterProvider {

    private ApplicationContext applicationContext;

    @Override
    public List<RequestTypeConverter> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(RequestTypeConverter.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
