package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileChannelService implements ChannelService {

    private static final String FILE_PATH = "channels.dat";
    private Map<UUID, Channel> channels;

    public FileChannelService() {
        this.channels = loadChannelsFromFile();
    }

    @SuppressWarnings("unchecked")
    private Map<UUID, Channel> loadChannelsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<UUID, Channel>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("channels.dat 파일이 없어 새로 생성합니다.");
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveChannelsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this.channels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Channel createChannel(String name) {
        Channel channel = new Channel(name);
        channels.put(channel.getId(), channel);
        saveChannelsToFile();
        return channel;
    }

    @Override
    public Channel find(UUID channelId) {
        return channels.get(channelId);
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }

    @Override
    public List<Channel> findByUser(User user) {
        return channels.values()
                .stream()
                .filter(channel -> channel.getUsers().stream()
                        .anyMatch(u -> u.getId().equals(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Channel channel) {
        channels.put(channel.getId(), channel);
        saveChannelsToFile();
    }

    @Override
    public void delete(UUID channelId) {
        channels.remove(channelId);
        saveChannelsToFile();
    }
}