package com.sprint.mission.discodeit.service;

import java.util.List;
import java.util.Map;

public interface MessageService {

    void sendMessage(String username, String content);

    boolean editMessage(String username, String content, String newContent);

    Map<String, List<String>> findAll();

    List<String> findAllByUsername(String username);

    boolean deleteOne(String username, String content);

    boolean deleteAll(String username);
}
