package io.chengine.filter;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;

public interface FilterChain {

    void doFilter(BotRequest request, BotResponse response);

}
