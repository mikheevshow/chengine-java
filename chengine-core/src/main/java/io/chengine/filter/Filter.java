package io.chengine.filter;

import io.chengine.BotRequest;
import io.chengine.BotResponse;

public interface Filter {

	void doFilter(BotRequest request, BotResponse response, FilterChain chain);

}
