package io.chengine.springframework.boot.autoconfigure;

import io.chengine.ChengineConfiguration;
import io.chengine.ChengineHandlerContext;
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
	public ChengineHandlerContext chengineHandlerContext(SpringHandlerProvider springHandlerProvider) {
		var configuration = new ChengineConfiguration.Builder()
			.addHandlerProvider(springHandlerProvider)
			.build();

		return new ChengineHandlerContext(configuration);
	}

}
