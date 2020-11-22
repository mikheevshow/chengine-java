package io.chengine.context;

import io.chengine.Mutates;
import io.chengine.command.CommandDescriptionAnnotationProcessor;
import io.chengine.handler.HandleCommandAnnotationProcessor;
import io.chengine.pipeline.PipelineAnnotationProcessor;
import io.chengine.command.i18n.CommandMetaInfo;
import io.chengine.handler.Handler;
import io.chengine.handler.HandlerCreationException;
import io.chengine.handler.HandlerRegistry;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.EventTrigger;
import io.chengine.pipeline.Pipeline;
import io.chengine.provider.HandlerProvider;
import io.chengine.provider.TriggerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static io.chengine.config.Configs.HANDLER_PROVIDERS;
import static io.chengine.config.Configs.TRIGGER_PROVIDERS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ChengineHandlerContext implements HandlerRegistry {

	private static final Logger log = LogManager.getLogger(ChengineHandlerContext.class);

	private static final String CLASS_NOT_ANNOTATED_MESSAGE = "Error handler registration. Annotation %s is not present on class %s.";
	private static final String HANDLER_CLASS_REGISTERED_MESSAGE = "Handler class %s registered in context %s";

    private final List<HandlerProvider> handlerProviders = new ArrayList<>();
    private final List<TriggerProvider> triggerProviders = new ArrayList<>();

    /**
     * The central part of Chengine. It stores map of string handler path and handler's method.
     * Key of every pair of Method and Handler object has following format: /{path1}/{param1}#/{path2}/...
     */
    @Mutates(by = HandleCommandAnnotationProcessor.class)
    private final Map<String, HandlerMethod> commandMethodMap = new HashMap<>();

    @Mutates(by = HandleCommandAnnotationProcessor.class)
    private final Map<Method, String> methodPathMap = new HashMap<>();

    @Mutates(by = PipelineAnnotationProcessor.class)
    private final Set<Pipeline> pipelineSet = new HashSet<>();

    private final Set<? super EventTrigger> triggerSet = new HashSet<>();

    private final Map<Class<? extends EventTrigger>, Pipeline> triggerPipelineMap = new HashMap<>();

    /**
     * A map, where a key represented by command pattern and value is a command meta inforamtion object,
     * which contains command localizations for example.
     */
    @Mutates(by = CommandDescriptionAnnotationProcessor.class)
    private final Map<String, CommandMetaInfo> commandMetaInfoMap = new HashMap<>();

	private ChengineHandlerContext() {
	}

    public ChengineHandlerContext(Properties chengineProperties) {
        List<HandlerProvider> handlerProviders = (List<HandlerProvider>) chengineProperties.get(HANDLER_PROVIDERS);
        List<TriggerProvider> triggerProviders = (List<TriggerProvider>) chengineProperties.get(TRIGGER_PROVIDERS);
        this.handlerProviders.addAll(handlerProviders);
        this.triggerProviders.addAll(triggerProviders);

        //todo register Triggers
        registerTriggers(
            this.triggerProviders.stream()
                .flatMap(triggerProvider -> triggerProvider.provideAll().stream())
                .collect(toList())
        );

        //todo вынести
        registerHandlers(
            this.handlerProviders.stream()
                .flatMap(handlerProvider -> handlerProvider.provideAll().stream())
                .collect(toList())
        );
    }

    public void registerTriggers(final Collection<? extends EventTrigger> triggers) {
        try {
            Objects.requireNonNull(triggers, "Trigger collection is empty");
            triggerSet.addAll(triggers);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
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

            var pipelineAnnotationProcessorInput = new PipelineAnnotationProcessor.Input(handlers, pipelineSet);
            new PipelineAnnotationProcessor().process(pipelineAnnotationProcessorInput);

            var commandDescriptionProcessorInput = new CommandDescriptionAnnotationProcessor.Input(handlers, commandMetaInfoMap, methodPathMap);
            new CommandDescriptionAnnotationProcessor().process(commandDescriptionProcessorInput);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void registerHandlerClass(final Object handler) {
        registerHandlers(Collections.singletonList(handler));
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
        return commandMethodMap.keySet();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Nullable
	public HandlerMethod get(String command) {
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
			.map(HandlerMethod::onObject)
			.collect(toSet());
	}

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
}
