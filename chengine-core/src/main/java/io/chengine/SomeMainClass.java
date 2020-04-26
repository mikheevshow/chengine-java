package io.chengine;

import io.chengine.annotation.Command;
import io.core.SomeClass;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SomeMainClass {

	public static void main(String[] args) {
		var someHandler =  new SomeClass();

		StringBuilder stringBuilder = new StringBuilder();

		someHandler.getClass().getDeclaredFields();

		var method = someHandler.getClass().getDeclaredMethods();

		var list = Arrays
			.stream(method)
			.filter(m -> m.isAnnotationPresent(Command.class))
			.collect(Collectors.toList());

	}

}
