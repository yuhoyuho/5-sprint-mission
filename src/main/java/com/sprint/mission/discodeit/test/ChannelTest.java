package com.sprint.mission.discodeit.test;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ChannelTest {

    static final ChannelService channelService = new JCFChannelService();

    public static void run() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("1 : 채널 추가");
            System.out.println("2 : 모든 채널 조회");
            System.out.println("3 : 채널 찾기");
            System.out.println("4 : 채널 정보 변경");
            System.out.println("5 : 채널 삭제");
            System.out.println("6 : 종료 ");

            int n = Integer.parseInt(br.readLine());

            if(n < 1 || n > 6) {
                System.out.println("다시 입력해주세요.");
                continue;
            }

            switch (n) {
                case 1 :
                    System.out.println("채널 이름 : ");
                    String channelName = br.readLine();

                    System.out.println("채널 정보 : ");
                    String channelDescription = br.readLine();

                    boolean result = channelService.addChannel(channelName, channelDescription);

                    if(result) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("failed");
                    }
                    break;
                case 2 :
                    List<Channel> list = channelService.findAll();
                    System.out.println(list.toString());
                    break;
                case 3 :
                    System.out.println("채널 이름 : ");
                    String find = br.readLine();

                    Channel channel = channelService.findOne(find);
                    if(channel != null) {
                        System.out.println(channel);
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 4 :
                    System.out.println("1 : 채널 이름 변경");
                    System.out.println("2 : 채널 정보 변경");
                    int m = Integer.parseInt(br.readLine());

                    if(m == 1) {
                        System.out.println("변경하고 싶은 채널 이름 : ");
                        System.out.println("변경할 채널 이름 : ");
                        String currentName = br.readLine();
                        String newName = br.readLine();
                        boolean updateName = channelService.updateChannelName(currentName, newName);

                        if(updateName) {
                            System.out.println("success");
                        }
                        else {
                            System.out.println("fail");
                        }
                        break;
                    }

                    if(m == 2) {
                        System.out.println("변경하고 싶은 채널 이름 : ");
                        System.out.println("변경할 채널 정보 : ");
                        String currentDescription = br.readLine();
                        String newDescription = br.readLine();
                        boolean updateDescription = channelService.updateChannelName(currentDescription, newDescription);

                        if(updateDescription) {
                            System.out.println("success");
                        }
                        else {
                            System.out.println("fail");
                        }
                        break;
                    }
                case 5 :
                    System.out.println("삭제할 채널 이름 : ");
                    String delete = br.readLine();

                    boolean deleteResult = channelService.deleteChannel(delete);

                    if(deleteResult) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 6 :
                    return;
            }
        }
    }
}
