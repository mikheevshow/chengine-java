package io.chengine.processor;

import io.chengine.command.Command;
import io.chengine.command.HandleCommand;
import io.chengine.connector.BotRequestContext;
import io.chengine.handler.HandlerNotFoundException;
import io.chengine.handler.HandlerRegistry;
import io.chengine.method.HandlerMethod;
import io.chengine.method.NoSuchMethodException;
import io.chengine.pipeline.PipelineDefinition;
import io.chengine.pipeline.StageDefinition;
import io.chengine.session.PipelineSessionInfo;
import io.chengine.session.Session;
import io.chengine.session.UserSessionContextHolder;

import java.util.Objects;

public class DefaultMethodResolver implements MethodResolver {

    private final HandlerRegistry handlerRegistry;

    public DefaultMethodResolver(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public HandlerMethod resolve(BotRequestContext request) {
        final Session session = UserSessionContextHolder.getSession();
        if (session != null) {
            if (session.inPipeline()) {
                final PipelineSessionInfo pipelineSessionInfo = session.getPipelineSessionInfo();
                final PipelineDefinition pipelineDefinition = pipelineSessionInfo.getPipeline();
                return pipelineDefinition
                        .getStageDefinitions()
                        .stream()
                        .filter(stageDefinition -> stageDefinition.getStep() == pipelineSessionInfo.getCurrentStep())
                        .findFirst()
                        .map(StageDefinition::getMethod)
                        .orElseThrow();
            }
        }
        if (HandleCommand.class.equals(request.shouldBeHandledByAnnotation())) {
            final Command command = Objects.requireNonNull(request.getCommand());
            final HandlerMethod handlerMethod = handlerRegistry.getHandlerByCommand(command);
            if (handlerMethod == null) {
                throw new HandlerNotFoundException("No method found matching command '" + command.path() + "'");
            }

            return handlerMethod;
        }

        final HandlerMethod handlerMethod = handlerRegistry.getSingleHandler(request.shouldBeHandledByAnnotation());

        if (handlerMethod == null) {
            throw new NoSuchMethodException("Can't find processing method for handle request");
        }

        return handlerMethod;
    }
}
