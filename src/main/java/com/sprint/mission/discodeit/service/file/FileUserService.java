package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.io.*;
import java.util.*;

public class FileUserService implements UserService {

    // 데이터를 저장할 파일 경로
    private static final String FILE_PATH = "users.dat";
    private Map<UUID, User> users;

    public FileUserService() {
        this.users = loadUsersFromFile();
    }

    @SuppressWarnings("unchecked")
    private Map<UUID, User> loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<UUID, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("users.dat 파일이 없어 새로 생성합니다.");
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>(); // 예외 발생 시 비어있는 초기화
        }
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this.users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        saveUsersToFile(); // 변경 사항을 파일에 저장
        return user;
    }

    @Override
    public User find(UUID userId) {
        return users.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
        saveUsersToFile(); // 변경 사항을 파일에 저장
    }

    @Override
    public void delete(UUID userId) {
        users.remove(userId);
        saveUsersToFile(); // 변경 사항을 파일에 저장
    }


    @Override
    public void joinChannel(User user, Channel channel) {
        if (!user.getChannels().contains(channel)) {
            user.getChannels().add(channel);
            channel.getUsers().add(user);
            update(user);
        }
    }
}