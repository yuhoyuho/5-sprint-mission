package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.test.ChannelTest;
import com.sprint.mission.discodeit.test.MessageTest;
import com.sprint.mission.discodeit.test.UserTest;

public class JavaApplication {
    public static void main(String[] args) throws Exception {
        UserTest userTest = new UserTest();
        userTest.run();

        ChannelTest channelTest = new ChannelTest();
        channelTest.run();

        MessageTest messageTest = new MessageTest();
        messageTest.run();
    }
}
