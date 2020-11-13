package io.chengine.springframework.boot.autoconfigure;

import io.chengine.ChengineConfiguration;
import io.chengine.context.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.*;
import io.chengine.springframework.provider.SpringHandlerProvider;
import io.chengine.springframework.provider.SpringTriggerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public MethodArgumentInspector methodArgumentInspector() {
		return new MethodArgumentInspector();
	}

	@Bean
	public CommandMethodResolver commandMethodResolver(ChengineHandlerContext chengineHandlerContext) {
		return new CommandMethodResolver(chengineHandlerContext);
	}

	@Bean
	public ResponseResolver responseResolver() {
		return new MethodReturnedTypeProcessor();
	}

	@Bean
	public MessageResolverFactory messageResolverFactory(CommandMethodResolver commandMethodResolver) {
		return new MessageResolverFactory(commandMethodResolver);
	}

	@Bean
	public ChengineMessageProcessor chengineMessageProcessor(
			CommandMethodResolver commandMethodResolver,
			MethodArgumentInspector methodArgumentInspector,
			ResponseResolver responseResolver

	) {

		return new ChengineMessageProcessor(
				commandMethodResolver,
				methodArgumentInspector,
				responseResolver
		);
	}

	@Bean
	public ChengineHandlerContext chengineHandlerContext(
		SpringHandlerProvider springHandlerProvider,
		SpringTriggerProvider springTriggerProvider
	) {
		var configuration = new ChengineConfiguration.Builder()
			.addHandlerProvider(springHandlerProvider)
			.addTriggerProvider(springTriggerProvider)
			.build();

		return new ChengineHandlerContext(configuration);
	}

}
