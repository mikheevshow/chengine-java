package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;

import java.util.ArrayList;
import java.util.List;

public class MessagePreprocessorInterceptorChain implements MessageProcessor<BotRequest, BotResponse> {

    private final List<MessageProcessor<BotRequest, BotResponse>> interceptors;

    private MessagePreprocessorInterceptorChain(List<MessageProcessor<BotRequest, BotResponse>> interceptors) {
        this.interceptors = interceptors;
    }

    public static MessageInterceptorChainBuilder builder() {
        return new MessageInterceptorChainBuilder();
    }

    private static class MessageInterceptorChainBuilder {

        private final List<MessageProcessor<BotRequest, BotResponse>> interceptors = new ArrayList<>();

        public MessageInterceptorChainBuilder addInterceptor(MessageProcessor<BotRequest, BotResponse> interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        public MessagePreprocessorInterceptorChain build() {
            return new MessagePreprocessorInterceptorChain(interceptors);
        }

    }

    @Override
    public void process(BotRequest request, BotResponse response) throws Exception {
        for (var interceptor : interceptors) {
            interceptor.process(request, response);
        }
    }
}
