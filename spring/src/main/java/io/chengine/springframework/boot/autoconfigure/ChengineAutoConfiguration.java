package io.chengine.springframework.boot.autoconfigure;

import io.chengine.Chengine;
import io.chengine.MessageProcessorAware;
import io.chengine.config.ChengineConfig;
import io.chengine.springframework.provider.*;
import io.chengine.springframework.stereotype.HandlerComponent;
import io.chengine.springframework.stereotype.PipelineComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "chengine", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ChengineAutoConfiguration {

	@Bean
	public SpringHandlerProvider springHandlerProvider() {
		return new SpringHandlerProvider();
	}

	@Bean
	public SpringRequestTypeConverterProvider springRequestTypeConverterProvider() {
		return new SpringRequestTypeConverterProvider();
	}

	@Bean
	public SpringResponseTypeHandlerProvider springResponseTypeHandlerProvider() {
		return new SpringResponseTypeHandlerProvider();
	}

	@Bean
	public SpringAnnotationProcessorProvider springChengineAnnotationProcessor() {
		return new SpringAnnotationProcessorProvider();
	}

	@Bean("chengine-props")
	public ChengineConfig chengineConfiguration(
			SpringHandlerProvider handlerProvider,
			SpringRequestTypeConverterProvider requestTypeConverterProvider,
			SpringResponseTypeHandlerProvider actionResponseHandlerProvider,
			SpringAnnotationProcessorProvider annotationProcessorProvider,
			List<MessageProcessorAware> messageProcessorAwares
	) {

		return new ChengineConfig()
				.addCustomHandlerAnnotation(HandlerComponent.class)
				.addCustomPipelineAnnotation(PipelineComponent.class)
				.addHandlers(handlerProvider.provideAll())
				.addMessageProcessorAwares(messageProcessorAwares)
				.addResponseHandlerProviders(actionResponseHandlerProvider.provideAll())
				.addAnnotationProcessors(annotationProcessorProvider.provideAll())
				.addConverters(requestTypeConverterProvider.provideAll());
	}

	@Bean
	public Chengine chengine(@Qualifier("chengine-props") ChengineConfig configuration) {
		return new Chengine(configuration);
	}

}
