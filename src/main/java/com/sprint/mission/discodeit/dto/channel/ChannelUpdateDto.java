package com.sprint.mission.discodeit.dto.channel;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChannelUpdateDto {
    private UUID id;
    private String name;
    private String description;
}
