package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.*;

public class JCFChannelRepository implements ChannelRepository {

    private final Map<UUID, Channel> channels = new HashMap<>();

    @Override
    public Channel save(Channel channel) {
        channels.put(channel.getId(), channel);
        return channel;
    }

    @Override
    public Optional<Channel> findById(UUID channelId) {
        return Optional.ofNullable(channels.get(channels));
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }

    @Override
    public void deleteById(UUID channelId) {
        channels.remove(channelId);
    }
}
