package com.sprint.mission.discodeit.test;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class MessageTest {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();
        JCFChannelService channelService = new JCFChannelService();
        JCFMessageService messageService = new JCFMessageService();

        User user = userService.createUser("user1", "<EMAIL>");
        User user2 = userService.createUser("user2", "<EMAIL>");

        Channel channel = channelService.createChannel("channel1");

        // create
        Message message1 = messageService.sendMessage(user, channel, "첫번째");
        System.out.println(message1);

        // update
        Message updateMessage = messageService.updateMessage(message1, "첫번째_수정");
        System.out.println(updateMessage);

        // delete
        System.out.println(channel.getMessages().toString());
        messageService.deleteMessage(message1);
        System.out.println(channel.getMessages().toString());
    }
}
