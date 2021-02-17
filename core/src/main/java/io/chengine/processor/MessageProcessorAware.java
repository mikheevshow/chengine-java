package io.chengine.processor;

public interface MessageProcessorAware<T, G> {

    void setMessageProcessor(MessageProcessor<T, G> messageProcessor);

}
