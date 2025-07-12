package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;

public interface ChannelService {

    boolean addChannel(String name, String description);

    List<Channel> findAll();

    Channel findOne(String name);

    boolean updateChannelName(String name, String newName);

    boolean updateChannelDescription(String name, String newDescription);

    boolean deleteChannel(String name);
}
