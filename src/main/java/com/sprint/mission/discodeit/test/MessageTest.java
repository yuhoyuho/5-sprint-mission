package com.sprint.mission.discodeit.test;

import com.sprint.mission.discodeit.service.jcf.JCFMessageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class MessageTest {

    static final JCFMessageService messageService = new JCFMessageService();

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("1 : 메세지 입력");
            System.out.println("2 : 메세지 전체 조회");
            System.out.println("3 : 이름으로 메세지 조회");
            System.out.println("4 : 메세지 수정");
            System.out.println("5 : 내용으로 메세지 삭제");
            System.out.println("6 : 메세지 전체 삭제");

            int n = Integer.parseInt(br.readLine());

            if(n < 1 || n > 6) {
                continue;
            }

            switch (n) {
                case 1 :
                    System.out.println("이름 : ");
                    String username = br.readLine();
                    System.out.println("내용 : ");
                    String content = br.readLine();

                    messageService.sendMessage(username, content);
                    break;
                case 2 :
                    Map<String, List<String>> all = messageService.findAll();
                    System.out.println(all.toString());
                    break;
                case 3 :
                    System.out.println("이름 : ");
                    String findName = br.readLine();
                    List<String> findContentByUsername = messageService.findAllByUsername(findName);
                    System.out.println(findContentByUsername.toString());
                    break;
                case 4 :
                    System.out.println("이름 : ");
                    String name = br.readLine();
                    System.out.println("수정 전 글 : ");
                    String oldContent = br.readLine();
                    System.out.println("수정 후 글 : ");
                    String newContent = br.readLine();

                    boolean edit = messageService.editMessage(name, oldContent, newContent);
                    if(edit) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 5 :
                    System.out.println("이름 : ");
                    String deleteName = br.readLine();
                    System.out.println("삭제할 글의 내용 : ");
                    String deleteContent = br.readLine();

                    boolean deleteOne = messageService.deleteOne(deleteName, deleteContent);
                    if(deleteOne) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 6 :
                    System.out.println("이름 : ");
                    String deleteAllName = br.readLine();
                    boolean deleteAll = messageService.deleteAll(deleteAllName);
                    if(deleteAll) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
            }
        }
    }
}
