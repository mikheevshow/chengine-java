package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.*;

public class TelegramPhoto extends Attachment {

    public TelegramPhoto(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(PHOTO, resourceName, file, inputStream);
    }
}
