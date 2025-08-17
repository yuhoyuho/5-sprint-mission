package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.channel.ChannelCreateDto;
import com.sprint.mission.discodeit.dto.channel.ChannelUpdateDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/channels")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    // 채널 생성
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Channel> create(@RequestBody ChannelCreateDto dto) {
        return ResponseEntity.status(201).body(channelService.create(dto));
    }

    // 채널 정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Channel> update(
            @RequestBody ChannelUpdateDto dto) {

        return ResponseEntity.ok(channelService.update(dto.getId(), dto.getName(), dto.getDescription()));
    }

    // 채널 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        channelService.delete(id);
        return ResponseEntity.ok().build();
    }


}
