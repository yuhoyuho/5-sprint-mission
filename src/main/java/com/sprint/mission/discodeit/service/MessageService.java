package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;

public interface MessageService {

    Message sendMessage(User user, Channel channel, String content);

    void deleteMessage(Message message);

    Message updateMessage(Message message, String newContent);
}
