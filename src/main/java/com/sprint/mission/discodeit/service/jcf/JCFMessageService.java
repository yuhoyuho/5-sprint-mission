package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.MessageService;

public class JCFMessageService implements MessageService {

    public Message sendMessage(User user, Channel channel, String content) {
        Message message = new Message(user, channel, content);
        channel.getMessages().add(message);
        user.getMessages().add(message);

        return message;
    }

    public void deleteMessage(Message message) {
        User user = message.getUser();
        Channel channel = message.getChannel();

        user.getMessages().remove(message);
        channel.getMessages().remove(message);
    }

    public Message updateMessage(Message message, String newContent) {
        User user = message.getUser();
        Channel channel = message.getChannel();

        for(Message m : user.getMessages()) {
            if(m.getId().equals(message.getId())) {
                m.setContent(newContent);
            }
        }

        return message;
    }
}
