package io.chengine.springframework.provider;

import io.chengine.commons.Converter;
import io.chengine.commons.RequestTypeConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.stream.Collectors;

public class SpringRequestTypeConverterProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public List<Converter<?, ?>> provideAll() {
        return applicationContext
                .getBeansOfType(RequestTypeConverter.class)
                .values()
                .stream()
                .map(c -> (Converter<?, ?>) c)
                .collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
