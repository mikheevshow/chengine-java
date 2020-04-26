package io.chengine.provider;

import io.chengine.annotation.Handler;
import io.chengine.springframework.stereotype.ComponentHandler;

import java.util.List;

public interface HandlerProvider {

	/**
	 * For handler registration it's should be annotated by {@link Handler} or
	 * {@link ComponentHandler} annotations.
	 *
	 * @return handlers :)
	 */
	List<?> provideAll();

}
