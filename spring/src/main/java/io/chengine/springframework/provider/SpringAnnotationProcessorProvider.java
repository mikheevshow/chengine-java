package io.chengine.springframework.provider;

import io.chengine.annotation.AnnotationProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SpringAnnotationProcessorProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public List<AnnotationProcessor> provideAll() {
        return new ArrayList<>(applicationContext.getBeansOfType(AnnotationProcessor.class).values());
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
