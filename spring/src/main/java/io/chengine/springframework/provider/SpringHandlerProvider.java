package io.chengine.springframework.provider;

import io.chengine.handler.Handler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SpringHandlerProvider implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public List<Object> provideAll() {
		return new ArrayList<>(applicationContext.getBeansWithAnnotation(Handler.class).values());
	}

	@Override
	public void setApplicationContext(@NonNull final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
