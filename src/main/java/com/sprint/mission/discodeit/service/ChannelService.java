package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface ChannelService {

    Channel createChannel(String name);

    Channel find(UUID channelId);

    List<Channel> findAll();

    List<Channel> findByUser(User user);

    void update(Channel channel);

    void delete(UUID channelId);
}
