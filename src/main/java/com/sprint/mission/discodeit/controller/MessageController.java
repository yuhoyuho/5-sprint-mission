package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.message.MessageCreateDto;
import com.sprint.mission.discodeit.dto.message.MessageUpdateDto;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 메세지 생성
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody MessageCreateDto dto) {
        return ResponseEntity.status(201).body(messageService.create(dto));
    }

    // 메세지 수정
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Message> update(
            @RequestBody MessageUpdateDto dto) {
        return ResponseEntity.ok(messageService.update(dto.messageId(), dto.newContent()));
    }

    // 메세지 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 특정 채널 메세지 조회
    public ResponseEntity<Message> find(@PathVariable UUID channelId) {
        return ResponseEntity.ok(messageService.find(channelId));
    }
}
