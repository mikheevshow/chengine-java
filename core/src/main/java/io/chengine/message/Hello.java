package io.chengine.message;

import io.chengine.connector.Message;

public class Hello {

    public Edit someMethod() {
        return Edit
                .message()
                .setText(() -> "New text")
                .done();
    }

}
