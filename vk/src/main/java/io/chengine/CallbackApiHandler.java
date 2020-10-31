package io.chengine;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.messages.Message;

public class CallbackApiHandler extends CallbackApiLongPoll {

    public CallbackApiHandler(VkApiClient client, UserActor actor, int groupId) {
        super(client, actor, groupId);
    }

    public CallbackApiHandler(VkApiClient client, GroupActor actor) {
        super(client, actor);
    }

    public CallbackApiHandler(VkApiClient client, UserActor actor, int groupId, int waitTime) {
        super(client, actor, groupId, waitTime);
    }

    public CallbackApiHandler(VkApiClient client, GroupActor actor, int waitTime) {
        super(client, actor, waitTime);
    }

    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        super.messageNew(groupId, secret, message);
    }
}
