package io.chengine.message.attachment;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Supplier;

public class Attachment {

    /**
     * Attachment media type
     */
    protected final String mediaType;

    @Nullable
    protected final String resourceName;

    @Nullable
    protected final File file;

    @Nullable
    protected final InputStream inputStream;

    protected Attachment(
            final String mediaType,
            @Nullable final String resourceName,
            @Nullable final File file,
            @Nullable final InputStream inputStream) {

        Objects.requireNonNull(mediaType);

        this.mediaType = mediaType;
        this.resourceName = resourceName;
        this.file = file;
        this.inputStream = inputStream;
    }

    public static AttachmentBuilder builder() {
        return new AttachmentBuilder();
    }

    public static class AttachmentBuilder {

        private String mediaType;
        private String resourceName;
        private File file;
        private InputStream inputStream;

        public AttachmentBuilder setMediaType(Supplier<String> mediaType) {
            this.mediaType = Objects.requireNonNull(mediaType.get(), "Attachment's media type can't be null");
            return this;
        }

        public AttachmentBuilder setFile(Supplier<File> file) {
            this.file = file.get();
            return this;
        }

        public AttachmentBuilder setResourceName(@Nonnull Supplier<String> resourceName) {
            if (resourceName == null) {
                throw new NullPointerException("Resource name supplier marked as nonnull, but is null");
            }

            this.resourceName = resourceName.get();
            return this;
        }

        public Attachment build() {
            return new Attachment(mediaType, resourceName, file, inputStream);
        }
    }

    public String mediaType() {
        return mediaType;
    }

    public File file() {
        return file;
    }

    @Nullable
    public InputStream inputStream() {
        return inputStream;
    }
}
