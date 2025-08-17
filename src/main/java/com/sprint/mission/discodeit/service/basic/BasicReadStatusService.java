package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.readStatus.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.readStatus.ReadStatusUpdateDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicReadStatusService implements ReadStatusService {

    private final ReadStatusRepository readStatusRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Override
    public void create(ReadStatusCreateDto dto) {
        // 1. User와 Channel이 실제로 존재하는지 확인
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + dto.userId()));
        Channel channel = channelRepository.findById(dto.channelId())
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id: " + dto.channelId()));

        // 2. 이미 해당 유저와 채널에 대한 ReadStatus가 있는지 확인하여 중복 생성 방지
        readStatusRepository.findByUserIdAndChannelId(dto.userId(), dto.channelId())
                .ifPresent(rs -> {
                    throw new IllegalArgumentException("ReadStatus already exists for this user and channel.");
                });

        // 3. 새 ReadStatus 엔티티를 생성하고 저장
        ReadStatus newReadStatus = new ReadStatus(user, channel);
        readStatusRepository.save(newReadStatus);
    }

    @Override
    public ReadStatus findById(UUID id) {
        return readStatusRepository.findById(id);
    }

    @Override
    public List<ReadStatus> findAllByUserId(UUID userId) {
        return readStatusRepository.findAllByUserId(userId);
    }

    @Override
    public void update(ReadStatusUpdateDto dto) {
        // 1. 수정할 ReadStatus를 userId와 channelId로 조회
        ReadStatus readStatus = readStatusRepository.findByUserIdAndChannelId(dto.userId(), dto.channelId())
                .orElseThrow(() -> new NoSuchElementException("ReadStatus not found for the given user and channel."));

        // 2. 엔티티의 updated() 메서드를 호출하여 updatedAt 타임스탬프 갱신
        readStatus.updated();

        // 3. 변경된 상태를 저장 (JCFRepository의 save는 덮어쓰기 로직을 포함)
        readStatusRepository.save(readStatus);
    }

    @Override
    public void deleteById(UUID id) {
        readStatusRepository.deleteById(id);
    }
}
