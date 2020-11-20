package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.MEDIA_GROUP;

public class TelegramMediaGroup extends Attachment {

    public TelegramMediaGroup(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(MEDIA_GROUP, resourceName, file, inputStream);
    }
}
