package io.chengine.method;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodTest {

    static class SomeClass {

        public String sayHello() {
            return "Hello";
        }

        public void sayNothing() {
        }
    }

    @Test
    public void TestSayHello() throws NoSuchMethodException {
        var someClass = new SomeClass();
        var method = new Method(SomeClass.class.getMethod("sayHello"), someClass);

        Assertions.assertEquals("Hello", method.invoke(String.class));
    }

    @Test
    public void TestSayNothing() throws NoSuchMethodException {
        var someClass = new SomeClass();
        var method = new Method(SomeClass.class.getMethod("sayNothing"), someClass);

        method.invokeVoid();
    }

}
