package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;

import static io.chengine.message.attachment.TelegramMediaType.AUDIO;

public class TelegramAudio extends Attachment {

    public TelegramAudio(
            @Nullable String resourceName,
            @Nullable File file,
            @Nullable InputStream inputStream) {

        super(AUDIO, resourceName, file, inputStream);
    }
}
