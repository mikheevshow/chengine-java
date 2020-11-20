package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.VIDEO_NOTE;

public class TelegramVideoNote extends Attachment {

    public TelegramVideoNote(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(VIDEO_NOTE, resourceName, file, inputStream);
    }
}
