package io.chengine.handler;

import java.util.ArrayList;
import java.util.List;

public class HandlerVisitorRegistry {

    public List<HandlerVisitor> visitors = new ArrayList<>();

    public HandlerVisitorRegistry() {}

    public void visit(HandlerMethod handlerMethod) {
        visitors.forEach(handlerVisitor -> handlerVisitor.visitHandler(handlerMethod));
    }

    public void add(HandlerVisitor handlerVisitor) {
        visitors.add(handlerVisitor);
    }

}
