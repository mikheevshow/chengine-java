package io.chengine.springframework.boot.autoconfigure;

import io.chengine.ChengineConfiguration;
import io.chengine.context.ChengineHandlerContext;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.processor.*;
import io.chengine.security.DefaultSecurityGuard;
import io.chengine.security.SecurityGuard;
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
			ResponseResolver responseResolver,
			SecurityGuard securityGuard

	) {

		return new ChengineMessageProcessor(
				commandMethodResolver,
				methodArgumentInspector,
				responseResolver,
				securityGuard
		);
	}

	@Bean
	public ChengineHandlerContext chengineHandlerContext(SpringHandlerProvider springHandlerProvider) {
		var configuration = new ChengineConfiguration.Builder()
			.addHandlerProvider(springHandlerProvider)
			.build();

		return new ChengineHandlerContext(configuration);
	}

	@Bean
	public SecurityGuard securityGuard() {
		return new DefaultSecurityGuard();
	}

}
