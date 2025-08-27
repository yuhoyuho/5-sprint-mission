package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class FileReadStatusRepository implements ReadStatusRepository {

    private final Path DIRECTORY;
    private final String EXTENSION = ".ser";

    public FileReadStatusRepository() {

        // 저장할 디렉토리가 존재하지 않으면 생성
        this.DIRECTORY = Paths.get(System.getProperty("user.dir"), "file-data-map", ReadStatus.class.getSimpleName());
        if (Files.notExists(DIRECTORY)) {
            try {
                Files.createDirectories(DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException("Could not create directory for ReadStatus files.", e);
            }
        }
    }

    private Path resolvePath(UUID userId, UUID channelId) {
        String fileName = userId.toString() + "_" + channelId.toString() + EXTENSION;
        return DIRECTORY.resolve(fileName);
    }

    @Override
    public void save(ReadStatus readStatus) {
        // 파일 경로 가져오기
        Path path = resolvePath(readStatus.getUserId(), readStatus.getChannelId());

        // 객체 직렬화를 통해 ReadStatus 객체를 파일에 사용
        try (
                FileOutputStream fos = new FileOutputStream(path.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(readStatus);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save ReadStatus to file.", e);
        }
    }

    @Override
    public Optional<ReadStatus> findByUserIdAndChannelId(UUID userId, UUID channelId) {
        Path path = resolvePath(userId, channelId);

        if (Files.notExists(path)) {
            return Optional.empty();
        }

        // 파일이 존재하면, 역직렬화를 통해 파일에서 객체를 읽음.
        try (
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return Optional.of((ReadStatus) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to read ReadStatus from file.", e);
        }
    }

    @Override
    public List<ReadStatus> findAllByUserId(UUID userId) {
        try (Stream<Path> paths = Files.walk(DIRECTORY)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith(userId.toString() + "_"))
                    .map(path -> {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                            return (ReadStatus) ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException("Failed to read ReadStatus from file: " + path, e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not walk directory: " + DIRECTORY, e);
        }
    }

    @Override
    public ReadStatus findById(UUID id) {
        try (Stream<Path> paths = Files.walk(DIRECTORY)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                            return (ReadStatus) ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            // 오류 발생 시 null 반환하여 필터링
                            return null;
                        }
                    })
                    .filter(status -> status != null && status.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (IOException e) {
            throw new RuntimeException("Could not walk directory: " + DIRECTORY, e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Stream<Path> paths = Files.walk(DIRECTORY)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                            ReadStatus status = (ReadStatus) ois.readObject();
                            if (status.getId().equals(id)) {
                                Files.delete(path);
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            // 파일 읽기 오류는 무시하고 계속 진행
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Could not walk directory for deletion: " + DIRECTORY, e);
        }
    }
}
