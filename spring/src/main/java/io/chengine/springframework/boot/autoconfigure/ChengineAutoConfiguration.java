package io.chengine.springframework.boot.autoconfigure;

import io.chengine.Chengine;
import io.chengine.MessageProcessorAware;
import io.chengine.provider.*;
import io.chengine.springframework.provider.*;
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

	@Bean
	public SpringRequestTypeConverterProvider springRequestTypeConverterProvider() {
		return new SpringRequestTypeConverterProvider();
	}

	@Bean
	public SpringResponseTypeHandlerProvider springResponseTypeHandlerProvider() {
		return new SpringResponseTypeHandlerProvider();
	}

	@Bean
	public SpringChengineAnnotationProcessor springChengineAnnotationProcessor() {
		return new SpringChengineAnnotationProcessor();
	}

	@Bean("chengine-props")
	public Properties chengineConfiguration(
			HandlerProvider handlerProvider,
			TriggerProvider triggerProvider,
			RequestTypeConverterProvider requestTypeConverterProvider,
			ResponseTypeHandlerProvider responseTypeHandlerProvider,
			AnnotationProcessorProvider annotationProcessorProvider,
			List<MessageProcessorAware> messageProcessorAwares
	) {

		final Properties properties = new Properties();
		properties.put(ENABLE_PLAIN_TEXT_MAPPING, false);
		properties.put(HANDLER_PROVIDERS, Collections.singletonList(handlerProvider));
		properties.put(TRIGGER_PROVIDERS, Collections.singletonList(triggerProvider));
		properties.put(REQUEST_HANDLERS_AWARE, messageProcessorAwares);
		properties.put(REQUEST_TYPE_CONVERTER_AWARE, requestTypeConverterProvider);
		properties.put(RESPONSE_TYPE_HANDLER_AWARE, responseTypeHandlerProvider);
		properties.put(ANNOTATION_PROCESSORS_AWARE, annotationProcessorProvider);

		return properties;
	}

	@Bean
	public Chengine chengine(@Qualifier("chengine-props") Properties configuration) {
		return new Chengine(configuration);
	}

}
