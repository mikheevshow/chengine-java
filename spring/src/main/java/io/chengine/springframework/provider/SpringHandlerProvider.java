package io.chengine.springframework.provider;

import io.chengine.annotation.Handler;
import io.chengine.provider.HandlerProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

public class SpringHandlerProvider implements HandlerProvider, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public List<?> provideAll() {
		return new ArrayList<>(applicationContext.getBeansWithAnnotation(Handler.class).values());
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
