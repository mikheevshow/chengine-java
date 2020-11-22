package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.*;

public class TelegramAnimation extends Attachment {

    public TelegramAnimation(
            @Nullable final String resourceName,
            @Nullable final File file,
            @Nullable final InputStream inputStream) {

        super(ANIMATION, resourceName, file, inputStream);
    }
}
