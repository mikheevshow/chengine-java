package io.chengine.method;

import io.chengine.command.CommandParameter;
import io.chengine.commons.Converter;
import io.chengine.connector.BotRequestContext;

import java.lang.reflect.Method;

public class MethodArgumentInspector {

	public Object[] inspectAndGetArguments(BotRequestContext request, Method method) {
		var parameters = method.getParameters();
		var args = new Object[parameters.length];
		var paramIndex = 0;
		for (var parameter : parameters) {
			var commandParameter = parameter.getAnnotation(CommandParameter.class);
			if (commandParameter != null) {
				var paramName = commandParameter.value();
				var command = request.getCommand();
				var value = command.getParam(paramName);
				var typedValue = stringToType(value, parameter.getType());
				args[paramIndex] = typedValue;
			} else {
				args[paramIndex] = extractParameterWithType(request, parameter.getType());
			}

			paramIndex++;
		}

		return args;
	}

	private Object extractParameterWithType(BotRequestContext request, Class<?> clazz) {

		if(BotRequestContext.class.equals(clazz)) {
			return request;
		}

		if (request.contains(clazz)) {
			return request.get(clazz);
		}

		if (request.hasConverterToType(clazz)) {
			try {
				final Converter<Object, Object> converter = request.getConverterToType(clazz);
				final Method method = converter.getClass().getMethod("convert");
				final Class<?> convertFromType = method.getParameterTypes()[0];
				if (request.contains(convertFromType)) {
					final Object fromObject = request.get(convertFromType);
					return converter.convert(fromObject);
				}
			} catch (java.lang.NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		}

		throw new RuntimeException("Can't extract parameters " + clazz.getName());
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
