package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;

public class FileMessageService implements MessageService {

    private final UserService userService;
    private final ChannelService channelService;

    public FileMessageService(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @Override
    public Message createMessage(User user, Channel channel, String content) {
        Message message = new Message(user, channel, content);
        channel.getMessages().add(message);
        user.getMessages().add(message);

        userService.update(user);
        channelService.update(channel);

        return message;
    }

    @Override
    public void deleteMessage(Message message) {
        User user = message.getUser();
        Channel channel = message.getChannel();

        user.getMessages().remove(message);
        channel.getMessages().remove(message);

        userService.update(user);
        channelService.update(channel);
    }

    @Override
    public Message update(Message message, String newContent) {

        message.setContent(newContent);

        userService.update(message.getUser());
        channelService.update(message.getChannel());

        return message;
    }

    @Override
    public Message find(Message message, String content) {
        User user = message.getUser();
        for (Message m : user.getMessages()) {
            if (m.getContent().equals(content)) {
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