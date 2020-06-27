package io.chengine.method;

import io.chengine.annotation.CommandParameter;
import io.chengine.connector.BotRequest;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MethodArgumentInspector {

	private final Set<Class<?>> injectableTypes;

	public MethodArgumentInspector() {
		injectableTypes = new HashSet<>();
		injectableTypes.add(BotRequest.class);
		for (var method : BotRequest.class.getDeclaredMethods()) {
			injectableTypes.add(method.getReturnType());
		}
	}

	public Object[] inspectAndGetArguments(BotRequest request, Method method) {
		var parameters = method.getParameters();
		var args = new Object[parameters.length];
		var paramIndex = 0;
		for (var parameter : parameters) {
			var commandParameter = parameter.getAnnotation(CommandParameter.class);
			if (commandParameter != null) {
				var paramName = commandParameter.value();
				var command = request.message().command();
				var value = command.getParam(paramName);
				var typedValue = stringToType(value, parameter.getType());
				args[paramIndex] = typedValue;
			} else if (injectableTypes.contains(parameter.getType())) {
				args[paramIndex] = extractParameterWithType(request, parameter.getType());
			} else {
				throw new RuntimeException("");
			}

			paramIndex++;
		}

		return args;
	}

	private Object extractParameterWithType(BotRequest request, Class<?> clazz) {
		if(BotRequest.class.equals(clazz)) {
			return request;
		}

		for (var method : BotRequest.class.getDeclaredMethods()) {
			if (method.getReturnType().getTypeName().equals(clazz.getTypeName())) {
				try {
					return method.invoke(request);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		}

		return new RuntimeException("Can't find method in BotRequest.class with return type " + clazz.getName());
	}

	private Object stringToType(String s, Class<?> tClass) {
		if (tClass.equals(String.class)) {
			return s;
		} else if (tClass.equals(Integer.class)) {
			return Integer.parseInt(s);
		} else if (tClass.equals(Long.class)) {
			return Long.parseLong(s);
		} else if (tClass.equals(Boolean.class)) {
			return Boolean.parseBoolean(s);
		} else {
			throw new RuntimeException("Can't parse value: " + s + " to type " + tClass.getSimpleName());
		}
	}
}
