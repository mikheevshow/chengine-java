package io.chengine.filter;

import io.chengine.bot.BotRequest;
import io.chengine.bot.BotResponse;

public interface Filter {

	void doFilter(BotRequest request, BotResponse response, FilterChain chain);

}
