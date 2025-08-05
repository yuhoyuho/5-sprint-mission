package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;

public class BasicMessageService implements MessageService {

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    public BasicMessageService(UserRepository userRepository, ChannelRepository channelRepository) {
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }

    @Override
    public Message createMessage(User user, Channel channel, String content) {
        Message message = new Message(user, channel, content);
        user.getMessages().add(message);
        channel.getMessages().add(message);

        userRepository.save(user);
        channelRepository.save(channel);

        return message;
    }

    @Override
    public void deleteMessage(Message message) {
        User user = message.getUser();
        Channel channel = message.getChannel();

        user.getMessages().remove(message);
        channel.getMessages().remove(message);

        userRepository.save(user);
        channelRepository.save(channel);
    }

    @Override
    public Message update(Message message, String newContent) {
        message.setContent(newContent);

        userRepository.save(message.getUser());
        channelRepository.save(message.getChannel());

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
