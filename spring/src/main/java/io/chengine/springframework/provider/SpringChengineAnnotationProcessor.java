package io.chengine.springframework.provider;

import io.chengine.annotation.ChengineAnnotationProcessor;
import io.chengine.provider.AnnotationProcessorProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringChengineAnnotationProcessor implements ApplicationContextAware, AnnotationProcessorProvider {

    private ApplicationContext applicationContext;

    @Override
    public List<ChengineAnnotationProcessor> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(ChengineAnnotationProcessor.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
