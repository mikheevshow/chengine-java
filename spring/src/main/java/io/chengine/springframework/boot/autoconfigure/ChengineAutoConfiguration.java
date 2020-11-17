package io.chengine.springframework.boot.autoconfigure;

import io.chengine.Chengine;
import io.chengine.ChengineConfiguration;
import io.chengine.RequestHandler;
import io.chengine.provider.HandlerProvider;
import io.chengine.provider.TriggerProvider;
import io.chengine.springframework.provider.SpringHandlerProvider;
import io.chengine.springframework.provider.SpringTriggerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

	@Bean
	public ChengineConfiguration chengineConfiguration(
			HandlerProvider handlerProvider,
			TriggerProvider triggerProvider,
			List<RequestHandler> requestHandlers
	) {
		return ChengineConfiguration
				.builder()
				.addHandlerProvider(handlerProvider)
				.addTriggerProvider(triggerProvider)
				.build();
	}

	@Bean
	public Chengine chengine(ChengineConfiguration configuration) {
		return new Chengine(configuration);
	}

}
