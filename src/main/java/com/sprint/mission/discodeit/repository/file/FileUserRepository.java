package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.util.*;

public class FileUserRepository implements UserRepository {

    private static final String FILE_PATH = "users.dat";
    private Map<UUID, User> users;

    public FileUserRepository() {
        this.users = loadUsersFromFile();
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        saveUsersToFile(); // 변경 후 파일에 저장
        return user;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(UUID userId) {
        users.remove(userId);
        saveUsersToFile(); // 변경 후 파일에 저장
    }

    @SuppressWarnings("unchecked")
    private Map<UUID, User> loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<UUID, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("데이터 로딩 중 에러가 발생했습니다.", e);
        }
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this.users);
        } catch (IOException e) {
            throw new RuntimeException("데이터 저장 중 에러가 발생했습니다.", e);
        }
    }
}
