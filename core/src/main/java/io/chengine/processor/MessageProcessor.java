package io.chengine.processor;

@FunctionalInterface
public interface MessageProcessor<T, G> {

	void process(T request, G response);

}
