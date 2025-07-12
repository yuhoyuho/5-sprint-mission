package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.service.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCFMessageService implements MessageService {

    private final Map<String, List<String>> map = new HashMap<>();

    @Override
    public void sendMessage(String username, String content) {
        map.putIfAbsent(username, new ArrayList<>());
        map.get(username).add(content);
    }

    @Override
    public boolean editMessage(String username, String content, String newContent) {
        if(!map.containsKey(username)) {
            return false;
        }

        if(!map.get(username).contains(content)) {
            return false;
        }

        map.get(username).set(map.get(username).indexOf(content), newContent);
        return true;
    }

    @Override
    public Map<String, List<String>> findAll() {
        return map;
    }

    @Override
    public List<String> findAllByUsername(String username) {
        if(!map.containsKey(username)) {
            return null;
        }

        return map.get(username);
    }

    @Override
    public boolean deleteAll(String username) {
        if(map.containsKey(username)) {
            map.remove(username);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteOne(String username, String content) {
        if(map.containsKey(username)) {
            if(!map.get(username).contains(content)) {
                return false;
            }

            map.get(username).remove(content);
            return true;
        }

        return false;
    }
}
