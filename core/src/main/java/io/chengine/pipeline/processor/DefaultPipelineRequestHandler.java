//package io.chengine.pipeline.processor;
//
//import io.chengine.connector.BotRequestContext;
//import io.chengine.connector.BotResponse;
//import io.chengine.message.ActionResponse;
//import io.chengine.method.HandlerMethod;
//import io.chengine.method.MethodArgumentInspector;
//import io.chengine.session.SessionManager;
//import io.chengine.pipeline.StageDefinition;
//import io.chengine.pipeline.action.StageAction;
//import io.chengine.processor.AbstractActionResponseHandler;
//import io.chengine.processor.response.ResponseTypeHandlerFactory;
//import io.chengine.session.pipeline.PipelineSession;
//
//public class DefaultPipelineRequestHandler implements PipelineRequestHandler {
//
//    //private SessionManager sessionManager;
//    private MethodArgumentInspector methodArgumentInspector;
//    private ResponseTypeHandlerFactory responseTypeHandlerFactory;
//
//    @Override
//    public void handleRequest(BotRequestContext request, BotResponse response) {
//        //final PipelineSession session = (PipelineSession) sessionManager.getCurrentSession();
//        //final StageDefinition stage = session.currentStage();
//        final HandlerMethod handlerMethod = stage.getMethod();
//        final Object[] arguments = methodArgumentInspector.inspectAndGetArguments(request, handlerMethod.get());
//        final StageAction<?> stageAction = (StageAction<?>) handlerMethod.invoke(arguments);
//        final ActionResponse actionResponse = stageAction.execute();
//        final AbstractActionResponseHandler abstractActionResponseHandler = (AbstractActionResponseHandler) responseTypeHandlerFactory.get(actionResponse.getClass());
//        abstractActionResponseHandler.handle(handlerMethod, actionResponse, request, response);
//    }
//
//}
