package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.VOICE;

public class TelegramVoice extends Attachment {

    public TelegramVoice(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(VOICE, resourceName, file, inputStream);
    }
}
