package io.chengine.springframework.provider;

import io.chengine.pipeline.AbstractTrigger;

import java.util.List;

public interface TriggerProvider {

    /**
     * For handler registration it's should be annotated by {@link io.chengine.annotation.Trigger}
     *
     * @return triggers :)
     */
    List<? extends AbstractTrigger> provideAll();
}
