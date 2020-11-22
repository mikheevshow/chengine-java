package io.chengine.springframework.boot.autoconfigure;

import io.chengine.Chengine;
import io.chengine.RequestHandler;
import io.chengine.provider.HandlerProvider;
import io.chengine.provider.TriggerProvider;
import io.chengine.springframework.provider.SpringHandlerProvider;
import io.chengine.springframework.provider.SpringTriggerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static io.chengine.config.Configs.*;

@Configuration
public class ChengineAutoConfiguration {

	@Bean
	public SpringHandlerProvider springHandlerProvider() {
		return new SpringHandlerProvider();
	}

	@Bean
	public SpringTriggerProvider springTriggerProvider() {
		return new SpringTriggerProvider();
	}

	@Bean("chengine-props")
	public Properties chengineConfiguration(
			HandlerProvider handlerProvider,
			TriggerProvider triggerProvider,
			List<RequestHandler> requestHandlers
	) {

		final Properties properties = new Properties();
		properties.put(ENABLE_PLAIN_TEXT_MAPPING, false);
		properties.put(HANDLER_PROVIDERS, Collections.singletonList(handlerProvider));
		properties.put(TRIGGER_PROVIDERS, Collections.singletonList(triggerProvider));
		properties.put(REQUEST_HANDLERS_AWARE, requestHandlers);

		return properties;
	}

	@Bean
	public Chengine chengine(@Qualifier("chengine-props") Properties configuration) {
		return new Chengine(configuration);
	}

}
