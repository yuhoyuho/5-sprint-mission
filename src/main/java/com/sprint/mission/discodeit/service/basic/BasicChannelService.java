package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class BasicChannelService implements ChannelService {

    private final ChannelRepository channelRepository;

    public BasicChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }


    @Override
    public Channel createChannel(String name) {
        Channel channel = new Channel(name);
        return channelRepository.save(channel);
    }

    @Override
    public Channel find(UUID channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new NoSuchElementException("채널을 찾을 수 없습니다. ID : " + channelId));
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public List<Channel> findByUser(User user) {
        return channelRepository.findAll()
                .stream()
                .filter(channel -> channel.getUsers().stream()
                        .anyMatch(u -> u.getId().equals(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Channel channel) {
        channelRepository.save(channel);
    }

    @Override
    public void delete(UUID channelId) {
        channelRepository.deleteById(channelId);
    }
}
