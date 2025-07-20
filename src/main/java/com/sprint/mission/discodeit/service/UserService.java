package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(String name, String email);

    User find(UUID userId);

    List<User> findAll();

    void update(User user);

    void delete(UUID userId);

    void joinChannel(User user, Channel channel);
}
