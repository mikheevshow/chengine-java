package io.chengine.sender;

import io.chengine.connector.Message;
import io.chengine.message.Delete;
import io.chengine.message.Edit;

public interface MessageSender {

    void send(Message message);

    void edit(Edit message);

    void delete(Delete message);

}
