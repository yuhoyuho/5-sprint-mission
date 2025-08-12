package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class JCFReadStatusRepository implements ReadStatusRepository {

    // 동시성 문제 때문에 CopyOnWriteArrayList 사용
    private final List<ReadStatus> data = new CopyOnWriteArrayList<>();

    public Optional<ReadStatus> findById(UUID id) {
        return data.stream()
                .filter(status -> status.getId().equals(id))
                .findFirst();
    }

    public List<ReadStatus> findAllByUserId(UUID userId) {
        return data.stream()
                .filter(status -> status.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        data.removeIf(status -> status.getId().equals(id));
    }

    @Override
    public void save(ReadStatus readStatus) {
        Optional<ReadStatus> existing = this.findByUserIdAndChannelId(readStatus.getUserId(), readStatus.getChannelId());

        existing.ifPresent(data::remove);

        data.add(readStatus);
    }

    @Override
    public Optional<ReadStatus> findByUserIdAndChannelId(UUID userId, UUID channelId) {
        return data.stream()
                // 수정된 부분: rs.getUser().getId() -> rs.getUserId()
                .filter(rs -> rs.getUserId().equals(userId) && rs.getChannelId().equals(channelId))
                .findFirst();
    }
}
