package io.chengine.method;

import java.util.concurrent.Callable;

public enum ReturnableTypes {

	CALLABLE(Callable.class);

	private Class<?> clazz;

	ReturnableTypes(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}
}
