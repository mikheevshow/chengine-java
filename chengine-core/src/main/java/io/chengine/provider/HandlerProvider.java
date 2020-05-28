package io.chengine.provider;

import io.chengine.annotation.Handler;

import java.util.List;

public interface HandlerProvider {

	/**
	 * For handler registration it's should be annotated by {@link Handler}
	 *
	 * @return handlers :)
	 */
	List<?> provideAll();

}
