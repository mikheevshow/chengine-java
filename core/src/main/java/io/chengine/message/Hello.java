package io.chengine.message;

public class Hello {

    public Edit someMethod() {
        return Edit
                .message()
                .setText(() -> "New text")
                .done();
    }

}
