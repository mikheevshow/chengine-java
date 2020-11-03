package io.chengine.processor.response;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractResponseTypeHandler {

    protected static final Logger log = LogManager.getLogger(AbstractResponseTypeHandler.class);

    public abstract Class<?> supports();

    public void handle(Method method, Object returnedObject, BotRequest request, BotResponse response) {
        log.info("Process returned object of type: {}", this::supports);
        process(method, returnedObject, request, response);
    }

    protected abstract void process(Method method, Object returnedObject, BotRequest request, BotResponse response);

}
