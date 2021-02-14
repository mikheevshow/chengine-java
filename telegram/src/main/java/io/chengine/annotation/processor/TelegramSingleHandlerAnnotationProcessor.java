package io.chengine.annotation.processor;

import io.chengine.annotation.*;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class TelegramSingleHandlerAnnotationProcessor extends AbstractSingleHandlerAnnotationProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<? extends Annotation>> supports() {
        final Set<Class<? extends Annotation>> classes = new HashSet<>();
        classes.add(TelegramHandleText.class);
        classes.add(TelegramHandlePoll.class);
        classes.add(TelegramHandlePollAnswer.class);
        classes.add(TelegramHandlePayment.class);
        classes.add(TelegramHandleMedia.class);
        classes.add(TelegramHandleLocation.class);
        classes.add(TelegramHandleContact.class);
        classes.add(TelegramHandleInlineQuery.class);
        classes.add(TelegramChosenInlineQuery.class);
        return classes;
    }
}
