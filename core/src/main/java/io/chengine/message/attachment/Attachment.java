package io.chengine.message.attachment;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Objects;
import java.util.function.Supplier;

public class Attachment {

    private final MediaType mediaType;

    @Nullable
    private final File file;

    private Attachment(
            final MediaType mediaType,
            @Nullable final File file) {

        this.mediaType = mediaType;
        this.file = file;
    }

    public static AttachmentBuilder builder() {
        return new AttachmentBuilder();
    }

    private static class AttachmentBuilder {

        private MediaType mediaType;
        private File file;

        public AttachmentBuilder setMediaType(Supplier<MediaType> mediaType) {
            this.mediaType = Objects.requireNonNull(mediaType.get(), "Attachment's media type can't be null");
            return this;
        }

        public AttachmentBuilder setFile(Supplier<File> file) {
            this.file = file.get();
            return this;
        }

        public Attachment build() {
            return new Attachment(mediaType, file);
        }
    }

    public MediaType mediaType() {
        return mediaType;
    }

    public File file() {
        return file;
    }
}
