//package io.chengine;
//
//import com.vk.api.sdk.client.VkApiClient;
//import com.vk.api.sdk.client.actors.GroupActor;
//import com.vk.api.sdk.httpclient.HttpTransportClient;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class VkLongPoolingBot {
//
//    private final HttpTransportClient client = new HttpTransportClient();
//    private final VkApiClient apiClient = new VkApiClient(client);
//    private final GroupActor groupActor;
//
//    public VkLongPoolingBot(
//            final int groupId,
//            final String token,
//            final int serverId,
//            final String code) {
//
//        groupActor = new GroupActor(groupId, token);
//        try {
//            apiClient
//                    .groups()
//                    .setCallbackSettings(groupActor, serverId)
//                    .messageNew(true)
//                    .execute();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//
//    }
//
//}
