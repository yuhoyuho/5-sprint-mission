package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.user.JoinDto;
import com.sprint.mission.discodeit.dto.user.UpdateDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserStatusService userStatusService;

    // 사용자 등록
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody JoinDto dto) {

        return ResponseEntity.status(201).body(userService.create(dto));
    }

    // 사용자 정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<User> update(
            @RequestBody UpdateDto dto) {

        return ResponseEntity.ok(userService.update(dto));
    }

    // 사용자 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 모든 사용자 조회
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 사용자의 온라인 상태 업데이트
    @RequestMapping(value = "/{id}/status", method = RequestMethod.PATCH)
    public ResponseEntity<Boolean> updateStatus(
            @PathVariable UUID id) {

        userStatusService.updateLastActive(userService.find(id));
        return ResponseEntity.ok(userStatusService.isActive(id));
    }
}
