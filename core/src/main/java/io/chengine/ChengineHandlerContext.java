package io.chengine;

import io.chengine.annotation.Handler;
import io.chengine.annotation.Mutates;
import io.chengine.annotation.processor.CommandDescriptionAnnotationProcessor;
import io.chengine.annotation.processor.HandleCommandAnnotationProcessor;
import io.chengine.command.i18n.CommandMetaInfo;
import io.chengine.provider.HandlerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ChengineHandlerContext implements HandlerRegistry {

	private final static Logger log = LogManager.getLogger(ChengineHandlerContext.class);

	private final static String CLASS_NOT_ANNOTATED_MESSAGE = "Error handler registration. Annotation %s is not present on class %s.";
	private final static String HANDLER_CLASS_REGISTERED_MESSAGE = "Handler class %s registered in context %s";

	/**
	 * The central part of Chengine. It stores map of string handler path and handler's method.
	 * Key of every pair of Method and Handler object has following format: /{path1}/{param1}#/{path2}/...
	 */
	@Mutates(by = HandleCommandAnnotationProcessor.class)
	private final Map<String, io.chengine.method.Method> commandMethodMap = new HashMap<>();

	@Mutates(by = HandleCommandAnnotationProcessor.class)
	private final Map<Method, String> methodPathMap = new HashMap<>();

	/**
	 * A map, where a key represented by command pattern and value is a command meta inforamtion object,
	 * which contains command localizations for example.
	 */
	@Mutates(by = CommandDescriptionAnnotationProcessor.class)
	private final Map<String, CommandMetaInfo> commandMetaInfoMap = new HashMap<>();

	private ChengineHandlerContext() {
	}

	public ChengineHandlerContext(ChengineConfiguration chengineConfiguration) {
		List<HandlerProvider> handlerProviders = chengineConfiguration.getHandlerProviders();

		if (handlerProviders != null) {
			registerHandlers(
					handlerProviders
							.stream()
							.flatMap(handlerProvider -> handlerProvider.provideAll().stream())
							.collect(toList())
			);
		}
	}

	public void registerHandlers(final Collection<?> handlers) {
		try {

			Objects.requireNonNull(handlers, "Handler collection is empty");
			handlers.forEach(handler -> {
				final Class<?> handlerClass = handler.getClass();
				final Annotation[] handlerClassAnnotations = handlerClass.getDeclaredAnnotations();
				final Annotation annotationHandler = findHandlerAnnotationRecursively(handlerClassAnnotations);
				if (annotationHandler == null) {
					throw new HandlerCreationException(String.format(CLASS_NOT_ANNOTATED_MESSAGE, Handler.class.getCanonicalName(), handlerClass.getCanonicalName()));
				}
			});

			var handleCommandAnnotationProcessorInput = new HandleCommandAnnotationProcessor.Input(handlers, commandMethodMap, methodPathMap);
			new HandleCommandAnnotationProcessor().process(handleCommandAnnotationProcessorInput);

			var commandDescriptionProcessorInput = new CommandDescriptionAnnotationProcessor.Input(handlers, commandMetaInfoMap, methodPathMap);
			new CommandDescriptionAnnotationProcessor().process(commandDescriptionProcessorInput);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public void registerHandlerClass(final Object handler) {
		registerHandlers(Collections.singletonList(handler));
	}

	/**
	 *
	 */
//	public void registerHandlerClass(final Object handler) {
//		try {
//			Objects.requireNonNull(handler, "handler object can't be null");
//			final Class<?> handlerClass = handler.getClass();
//			final Annotation[] handlerClassAnnotations = handlerClass.getDeclaredAnnotations();
//			final Annotation annotationHandler = findHandlerAnnotationRecursively(handlerClassAnnotations);
//			if (annotationHandler == null) {
//				throw new HandlerCreationException(String.format(CLASS_NOT_ANNOTATED_MESSAGE, Handler.class.getCanonicalName(), handlerClass.getCanonicalName()));
//			}
//
//			String handlerAnnotationCommandPath = "";
//			for (var annotation : handlerClassAnnotations) {
//				if (isHandler(annotation)) {
//					Method valueMethod = annotation.annotationType().getMethod("value");
//					handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
//					break;
//				} else if (annotatedByHandler(annotation)) { // See contract about inheritance of Handler annotation
//					Method valueMethod = annotation.annotationType().getMethod("command");
//					handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
//					break;
//				}
//			}
//
//			String finalHandlerAnnotationCommandPath = handlerAnnotationCommandPath;
//
//			Arrays
//				.stream(handler.getClass().getMethods())
//				.filter(method -> method.getAnnotation(HandleCommand.class) != null)
//				.forEach(method -> {
//					Annotation annotation = method.getAnnotation(HandleCommand.class);
//					try {
//						Method valueMethod = annotation.annotationType().getMethod("value");
//						String annotationCommandPathTemplate = valueMethod.invoke(annotation).toString();
//						String fullMethodCommandPathTemplate = finalHandlerAnnotationCommandPath + annotationCommandPathTemplate;
//
//						Annotation descriptionAnnotation = method.getAnnotation(CommandDescription.class);
//						if(Objects.nonNull(descriptionAnnotation)) {
//							Method descriptionMethod = descriptionAnnotation.annotationType().getMethod("description");
//							String[] descriptions = (String[]) descriptionMethod.invoke(descriptionAnnotation);
//
//							Arrays
//									.stream(descriptions)
//									.forEach(str -> {
//										String delimiter = " : ";
//										int delimiterPos = str.indexOf(delimiter);
//
//										Map<String, String> localeDescription =
//												commandDescriptions.getOrDefault(fullMethodCommandPathTemplate, new HashMap<>());
//										localeDescription.put(str.substring(0, delimiterPos), str.substring(delimiter.length() + delimiterPos));
//
//										commandDescriptions.put(fullMethodCommandPathTemplate, localeDescription);
//									});
//						}
//
//						if ("".equals(fullMethodCommandPathTemplate)) {
//							throw new RuntimeException("Command annotation value can't be empty if Handler annotation value is empty in class " + handler.getClass().getCanonicalName());
//						}
//
//						if (commandMethodMap.containsKey(fullMethodCommandPathTemplate)) {
//							throw new RuntimeException("Duplicate of methods with parameter " + fullMethodCommandPathTemplate);
//						}
//
//						//defaultCommandValidator.validateCommandTemplate(fullMethodCommandPathTemplate);
//
//						commandMethodMap.put(fullMethodCommandPathTemplate, io.chengine.method.Method.of(method, handler));
//
//					} catch (Exception ex) {
//						//log.error(ex.getMessage(), ex);
//					}
//				});
//
//
//			//log.info(commandHandlerMap.toString());
//			// Process registration
//
//			//log.info(String.format(HANDLER_CLASS_REGISTERED_MESSAGE, handlerClass.getCanonicalName(), this));
//		} catch (Exception ex) {
//			//log.error(ex.getMessage(), ex);
//		}
//	}


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
		return commandMethodMap.keySet();
	}

	/**
	 * {@inheritDoc}
	 * @return
	 */
	@Override
	@Nullable
	public io.chengine.method.Method get(String command) { //todo
		return commandMethodMap.get(command);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<?> getAllHandlers() {
		return commandMethodMap
			.values()
			.stream()
			.map(io.chengine.method.Method::onObject)
			.collect(toSet());
	}

	//****************************************************************************************************************

}
