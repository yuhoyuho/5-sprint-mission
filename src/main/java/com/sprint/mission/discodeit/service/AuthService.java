package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.auth.LoginResponse;

public interface AuthService {

    LoginResponse login(String username, String password);
}
