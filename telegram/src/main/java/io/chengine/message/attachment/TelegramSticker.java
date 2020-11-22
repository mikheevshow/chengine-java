package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.*;

public class TelegramSticker extends Attachment {

    public TelegramSticker(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(STICKER, resourceName, file, inputStream);
    }
}
