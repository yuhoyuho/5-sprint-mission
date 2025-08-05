package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.util.*;

public class FileChannelRepository implements ChannelRepository {

    private static final String FILE_PATH = "channels.dat";
    private Map<UUID, Channel> channels;

    public FileChannelRepository() {
        this.channels = loadChannelsFromFile();
    }

    @Override
    public Channel save(Channel channel) {
        channels.put(channel.getId(), channel);
        saveChannelsToFile();
        return channel;
    }

    @Override
    public Optional<Channel> findById(UUID channelId) {
        return Optional.ofNullable(channels.get(channelId));
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }

    @Override
    public void deleteById(UUID channelId) {
        channels.remove(channelId);
        saveChannelsToFile();
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
}
