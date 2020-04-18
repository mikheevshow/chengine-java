package io.chengine;

import java.lang.annotation.Annotation;
import java.util.List;

public interface HandlerFactory {

	Object getHandlerWithMapping(String command);

	<T> T getHandlerWithMappingAndClass(String command, Class<T> tClass);

	List<Object> getHandlersAnnotatedBy(Annotation annotation);

}
