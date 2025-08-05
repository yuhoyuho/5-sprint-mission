package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;

public class JCFMessageService implements MessageService {

    public Message createMessage(User user, Channel channel, String content) {
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

    public Message update(Message message, String newContent) {
        User user = message.getUser();
        for(Message m : user.getMessages()) {
            if(m.getId().equals(message.getId())) {
                m.setContent(newContent);
            }
        }

        return message;
    }

    @Override
    public Message find(Message message, String content) {
        User user = message.getUser();
        for(Message m : user.getMessages()) {
            if(m.getContent().equals(content)) {
                return m;
            }
        }

        return null;
    }

    @Override
    public List<Message> findAll(User user) {
        return user.getMessages();
    }
}
