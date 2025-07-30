package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;

import java.io.File;

public class JavaApplication {
    public static void main(String[] args) {
        System.out.println("FileService integration test");
        System.out.println("------------------------");

        new File("users.dat").delete();
        new File("channels.dat").delete();

        UserService userService = new FileUserService();
        ChannelService channelService = new FileChannelService();

        MessageService messageService = new FileMessageService(userService, channelService);

        User user = userService.createUser("이유호", "leeyuho@naver.com");
        Channel channel = channelService.createChannel("스프린트 5기");

        userService.joinChannel(user, channel);

        Message message = messageService.createMessage(user, channel, "안녕하세요.");
        Message message1 = messageService.createMessage(user, channel, "반갑습니다.");

        System.out.println("--- 데이터 생성 완료 ---");
        System.out.println("User: " + user);
        System.out.println("Channel: " + channel);
        System.out.println("Messages: " + channel.getMessages());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("BasicService + FileRepository 테스트");
        System.out.println("-----------------------------------------");

        new File("users.dat").delete();
        new File("channels.dat").delete();

        // JCF 방식으로 바꾸고 싶으면 이 부분에서 new JCF*Repository로 생성
        UserRepository userRepository = new FileUserRepository();
        ChannelRepository channelRepository = new FileChannelRepository();

        UserService userService1 = new BasicUserService(userRepository, channelRepository);
        ChannelService channelService1 = new BasicChannelService(channelRepository);
        MessageService messageService1 = new BasicMessageService(userRepository, channelRepository);

        System.out.println("\n--- 서비스 및 리포지토리 초기화 완료 ---");

        System.out.println("\n1. 유저 및 채널 생성...");
        User user1 = userService1.createUser("이유호", "leeyuho123@naver.com");
        Channel channel1 = channelService1.createChannel("codeIt sprint");
        System.out.printf("  - 유저 %s와 채널 %s 생성 완료\n", user1.getName(), channel1.getName());

        System.out.println("\n2. 유저가 채널에 참여...");
        userService1.joinChannel(user1, channel1);
        System.out.printf("  - %s님이 %s 채널에 참여했습니다.\n", user1.getName(), channel1.getName());

        System.out.println("\n3. 메시지 작성...");
        messageService.createMessage(user1, channel1, "안녕하세요.");
        messageService.createMessage(user1, channel1, "이유호입니다.");
        System.out.println("  - 2개의 메시지를 작성했습니다.");

        System.out.println("\n--- 최종 데이터 확인 ---");
        Channel persistedChannel = channelService1.find(channel1.getId());
        System.out.println("조회된 채널 이름: " + persistedChannel.getName());
        System.out.println("채널 참여자 수: " + persistedChannel.getUsers().size());
        System.out.println("채널 내 메시지 개수: " + persistedChannel.getMessages().size());

        System.out.println("\n프로젝트 폴더에 users.dat, channels.dat 파일이 생성/업데이트되었는지 확인해보세요.");
    }
}
