package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.List;
import java.util.UUID;

public interface UserStatusRepository {

    void save(UserStatus userStatus);

    List<UserStatus> findAll();

    UserStatus findByUserId(UUID userId);

    void deleteById(UUID id);
}
