package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.List;

public class JCFChannelService implements ChannelService {
    private final List<Channel> list = new ArrayList<>();

    @Override
    public boolean addChannel(String name, String description) {
        Channel channel = new Channel(name, description);
        if(isExisted(name)) {
            list.add(channel);
            return true;
        }
        return false;
    }

    @Override
    public List<Channel> findAll() {
        return list;
    }

    @Override
    public Channel findOne(String name) {
        for(Channel channel : list) {
            if(channel.getName().equals(name)) {
                return channel;
            }
        }

        return null;
    }

    @Override
    public boolean updateChannelName(String name, String newName) {
        for(Channel channel : list) {
            if(channel.getName().equals(name)) {
                channel.updateName(newName);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateChannelDescription(String name, String newDescription) {
        for(Channel channel : list) {
            if(channel.getName().equals(name)) {
                channel.updateDescription(newDescription);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteChannel(String name) {
        for(Channel channel : list) {
            if(channel.getName().equals(name)) {
                list.remove(channel);
                return true;
            }
        }

        return false;
    }

    public boolean isExisted(String name) {
        for(Channel channel : list) {
            if(channel.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }
}
