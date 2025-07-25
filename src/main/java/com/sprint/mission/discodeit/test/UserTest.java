package com.sprint.mission.discodeit.test;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class UserTest {

    static final JCFUserService userService = new JCFUserService();

    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();

        User user1 = userService.createUser("user1", "<EMAIL>");
        User user2 = userService.createUser("user2", "<EMAIL>");

        // find
        System.out.println(userService.find(user1.getId()).toString());

        // findAll
        System.out.println(userService.findAll().toString());

        // update
        user1.setName("update_name");
        userService.update(user1);
        System.out.println(userService.find(user1.getId()).toString());

        // delete
        userService.delete(user2.getId());
        System.out.println(userService.findAll().toString());
    }
}
