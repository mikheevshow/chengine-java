package io.chengine.processor;

public interface MessageProcessor<T> {

	void process(T request);

}
