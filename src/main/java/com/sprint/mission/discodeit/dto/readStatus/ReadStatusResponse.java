package com.sprint.mission.discodeit.dto.readStatus;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.time.Instant;
import java.util.UUID;

public record ReadStatusResponse(UUID channelId, Instant lastReadAt) {
    public static ReadStatusResponse from(ReadStatus readStatus) {
        return new ReadStatusResponse(readStatus.getChannelId(), readStatus.getUpdatedAt());
    }
}
