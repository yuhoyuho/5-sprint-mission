package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;

public interface MessageService {

    Message createMessage(User user, Channel channel, String content);

    void deleteMessage(Message message);

    Message update(Message message, String newContent);

    Message find(Message message, String content);

    List<Message> findAll(User user);
}
