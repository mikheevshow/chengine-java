package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.DOCUMENT;

public class TelegramDocument extends Attachment {

    public TelegramDocument(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(DOCUMENT, resourceName, file, inputStream);
    }
}
