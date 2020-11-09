package io.chengine.provider;

import io.chengine.annotation.dto.AbstractTrigger;

import java.util.List;

public interface TriggerProvider {

    /**
     * For handler registration it's should be annotated by {@link io.chengine.annotation.Trigger}
     *
     * @return triggers :)
     */
    List<? extends AbstractTrigger> provideAll();
}
