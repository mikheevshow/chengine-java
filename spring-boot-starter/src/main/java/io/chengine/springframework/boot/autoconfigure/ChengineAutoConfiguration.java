package io.chengine.springframework.boot.autoconfigure;

import io.chengine.ChengineConfiguration;
import io.chengine.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.ChengineMessageProcessor;
import io.chengine.processor.CommandMethodResolver;
import io.chengine.processor.MessageResolverFactory;
import io.chengine.springframework.provider.SpringHandlerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChengineAutoConfiguration {

	@Bean
	public SpringHandlerProvider springHandlerProvider() {
		return new SpringHandlerProvider();
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
	public MessageResolverFactory messageResolverFactory(CommandMethodResolver commandMethodResolver) {
		return new MessageResolverFactory(commandMethodResolver);
	}

	@Bean
	public ChengineMessageProcessor chengineMessageProcessor(CommandMethodResolver commandMethodResolver, MethodArgumentInspector methodArgumentInspector) {

		return new ChengineMessageProcessor(commandMethodResolver, methodArgumentInspector);
	}

	@Bean
	public ChengineHandlerContext chengineHandlerContext(SpringHandlerProvider springHandlerProvider) {
		var configuration = new ChengineConfiguration.Builder()
			.addHandlerProvider(springHandlerProvider)
			.build();

		return new ChengineHandlerContext(configuration);
	}

}
