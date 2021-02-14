package io.chengine.session;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequestContext;

public interface SessionKeyExtractor {

    BotApiIdentifier support();

    SessionKey extractFrom(BotRequestContext requestContext);

}
