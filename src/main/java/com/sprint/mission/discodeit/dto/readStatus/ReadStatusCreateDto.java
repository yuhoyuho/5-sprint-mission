package com.sprint.mission.discodeit.dto.readStatus;

import java.util.UUID;

public record ReadStatusCreateDto(
        UUID userId,
        UUID channelId
) {
}
