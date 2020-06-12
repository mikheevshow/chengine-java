package io.chengine;

import io.chengine.annotation.Command;
import io.chengine.annotation.Handler;
import io.chengine.provider.HandlerProvider;
import io.chengine.validation.CommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

//@Slf4j
public class ChengineHandlerContext implements HandlerRegistry {

	private final static String CLASS_NOT_ANNOTATED_MESSAGE = "Error handler registration. Annotation %s is not present on class %s.";
	private final static String HANDLER_CLASS_REGISTERED_MESSAGE = "Handler class %s registered in context %s";

	/**
	 * The central part of Chengine. It stores map of string handler path and handler's method.
	 * Key of every pair of Method and Handler object has following format (random sequences of
	 * path/params item)
	 *
	 * For ex.:
	 * /{path1}/{param1}#/{path2}/...
	 *
	 */
	private final HashMap<String, Pair<Method, Object>> commandHandlerMap = new HashMap<>();

	private final CommandValidator commandValidator = new CommandValidator();

	private ChengineHandlerContext() {

	}

	public ChengineHandlerContext(ChengineConfiguration chengineConfiguration) {
		List<HandlerProvider> handlerProviders = chengineConfiguration.getHandlerProviders();

		if (handlerProviders != null) {
			handlerProviders
				.stream()
				.flatMap(handlerProvider -> handlerProvider.provideAll().stream())
				.collect(toList())
				.forEach(this::registerHandlerClass);

		}
	}

	/**
	 *
	 */
	public void registerHandlerClass(final Object handler) {

		try {

			Objects.requireNonNull(handler, "handler object can't be null");
			final Class<?> handlerClass = handler.getClass();
			final Annotation[] handlerClassAnnotations = handlerClass.getDeclaredAnnotations();
			final Annotation annotationHandler = findHandlerAnnotationRecursively(handlerClassAnnotations);
			if (annotationHandler == null) {
				throw new HandlerCreationException(String.format(CLASS_NOT_ANNOTATED_MESSAGE, Handler.class.getCanonicalName(), handlerClass.getCanonicalName()));
			}

			String handlerAnnotationCommandPath = "";
			for (var annotation : handlerClassAnnotations) {
				if (isHandler(annotation)) {
					Method valueMethod = annotation.annotationType().getMethod("value");
					handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
					break;
				} else if (annotatedByHandler(annotation)) { // See contract about inheritance of Handler annotation
					Method valueMethod = annotation.annotationType().getMethod("command");
					handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
					break;
				}
			}

			String finalHandlerAnnotationCommandPath = handlerAnnotationCommandPath;

			Arrays
				.stream(handler.getClass().getMethods())
				.filter(method -> method.getAnnotation(Command.class) != null)
				.forEach(method -> {
					Annotation annotation = method.getAnnotation(Command.class);
					try {
						Method valueMethod = annotation.annotationType().getMethod("value");
						String annotationCommandPathTemplate = valueMethod.invoke(annotation).toString();
						String fullMethodCommandPathTemplate = finalHandlerAnnotationCommandPath + annotationCommandPathTemplate;

						if ("".equals(fullMethodCommandPathTemplate)) {
							throw new RuntimeException("Command annotation value can't be empty if Handler annotation value is empty in class " + handler.getClass().getCanonicalName());
						}

						if (commandHandlerMap.containsKey(fullMethodCommandPathTemplate)) {
							throw new RuntimeException("Duplicate of methods with parameter " + fullMethodCommandPathTemplate);
						}

						commandValidator.validateCommandTemplate(fullMethodCommandPathTemplate);

						commandHandlerMap.put(fullMethodCommandPathTemplate, Pair.of(method, handler));

					} catch (Exception ex) {
						//log.error(ex.getMessage(), ex);
					}
				});


			//log.info(commandHandlerMap.toString());
			// Process registration

			//log.info(String.format(HANDLER_CLASS_REGISTERED_MESSAGE, handlerClass.getCanonicalName(), this));
		} catch (Exception ex) {
			//log.error(ex.getMessage(), ex);
		}
	}


	//****************************************************************************************************************
	//
	//								HandlerRegistry interface implementation
	//
	//****************************************************************************************************************

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getAllPaths() {
		return commandHandlerMap.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Nullable
	public Pair<Method, Object> getHandlerMethod(String command) {
		return commandHandlerMap.get(command);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<?> getAllHandlers() {
		return commandHandlerMap
			.entrySet()
			.stream()
			.map(entryToHandler)
			.collect(toSet());
	}

	private final Function<Map.Entry<String, Pair<Method, Object>>, Object> entryToHandler = entry -> {

		Pair<Method, Object> value = entry.getValue();
		Objects.requireNonNull(value, "Value for key " + entry.getKey());
		return value.getRight();
	};


	//****************************************************************************************************************

	@Nullable
	private Annotation findHandlerAnnotationRecursively(final Annotation[] annotations) {
		if (annotations == null || annotations.length == 0)
			return null;

		for (final Annotation annotation : annotations) {
			if (isHandler(annotation))
				return annotation;
		}

		for (final Annotation annotation : annotations) {
			Annotation a = findHandlerAnnotationRecursively(annotation.annotationType().getAnnotations());
			if (a != null)
				return a;
		}

		return null;
	}

	private boolean isHandler(Annotation annotation) {
		return annotation.annotationType().equals(Handler.class);
	}

	private boolean annotatedByHandler(Annotation annotation) {
		Annotation[] annotations = annotation.annotationType().getDeclaredAnnotations();
		Annotation handlerAnnotation = findHandlerAnnotationRecursively(annotations);

		return handlerAnnotation != null;
	}

}
