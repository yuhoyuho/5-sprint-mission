package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class JavaApplication {
    public static void main(String[] args) {
        System.out.println("integration test");
        System.out.println("------------------------");

        JCFUserService userService = new JCFUserService();
        JCFMessageService messageService = new JCFMessageService();
        JCFChannelService channelService = new JCFChannelService();

        User user = userService.createUser("이유호", "EMAIL");
        Channel channel = channelService.createChannel("스프린트 5기");
        Message message = messageService.createMessage(user, channel, "안녕하세요.");
        Message message1 = messageService.createMessage(user, channel, "반갑습니다.");

        userService.joinChannel(user, channel);

        System.out.println(user);
        System.out.println(channel.toString());
        System.out.println(message.toString());
        System.out.println(message1.toString());

    }
}
