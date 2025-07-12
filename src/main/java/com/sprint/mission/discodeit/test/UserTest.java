package com.sprint.mission.discodeit.test;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserTest {

    static final JCFUserService userService = new JCFUserService();

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("1 : 회원가입");
            System.out.println("2 : 모든 사용자 조회");
            System.out.println("3 : 사용자 정보 찾기");
            System.out.println("4 : 사용자 이름 변경");
            System.out.println("5 : 사용자 비밀번호 변경");
            System.out.println("6 : 사용자 이메일 변경");
            System.out.println("7 : 사용자 주소 변경");
            System.out.println("8 : 회원 삭제");
            System.out.println("9 : 종료 ");

            int n = Integer.parseInt(br.readLine());

            if(n < 1 || n > 9) {
                System.out.println("다시 입력해주세요.");
                continue;
            }

            switch (n) {
                case 1:
                    System.out.println("이름 : ");
                    String name = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password = br.readLine();

                    System.out.println("이메일 : ");
                    String email = br.readLine();

                    System.out.println("주소 (xx시 xx구 xx동) : ");
                    String[] address = br.readLine().split(" ");
                    String city = address[0];
                    String street = address[1];
                    String zipcode = address[2];

                    boolean result = userService.addUser(password, name, email, city, street, zipcode);

                    if(result) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("failed");
                    }
                    break;
                case 2 :
                    List<User> list = userService.findAll();
                    System.out.println(list.toString());
                    break;
                case 3 :
                    System.out.println("이름 : ");
                    String name2 = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password2 = br.readLine();

                    User user = userService.findOne(name2, password2);
                    if(user != null) {
                        System.out.println(user);
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 4:
                    System.out.println("현재 이름 :");
                    String name3 = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password3 = br.readLine();

                    System.out.println("변경할 이름 : ");
                    String newName = br.readLine();

                    boolean result2 = userService.updateUserName(name3, password3, newName);

                    if(result2) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 5 :
                    System.out.println("이름 :");
                    String name4 = br.readLine();

                    System.out.println("현재 비밀번호 : ");
                    String password4 = br.readLine();

                    System.out.println("변경할 비밀번호 : ");
                    String newPassword = br.readLine();

                    boolean result3 = userService.updateUserPassword(name4, password4, newPassword);

                    if(result3) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 6 :
                    System.out.println("현재 이름 :");
                    String name5 = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password5 = br.readLine();

                    System.out.println("변경할 이메일 : ");
                    String newEmail = br.readLine();

                    boolean result4 = userService.updateUserEmail(name5, password5, newEmail);

                    if(result4) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 7 :
                    System.out.println("현재 이름 :");
                    String name6 = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password6 = br.readLine();

                    System.out.println("변경할 주소 (xx시 xx구 xx동) : ");
                    String[] newAddress = br.readLine().split(" ");
                    String newCity = newAddress[0];
                    String newStreet = newAddress[1];
                    String newZipcode = newAddress[2];

                    boolean result5 = userService.updateUserAddress(name6, password6, newCity, newStreet, newZipcode);

                    if(result5) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 8 :
                    System.out.println("이름 :");
                    String name7 = br.readLine();

                    System.out.println("비밀번호 : ");
                    String password7 = br.readLine();

                    boolean result6 = userService.deleteUser(name7, password7);

                    if(result6) {
                        System.out.println("success");
                    }
                    else {
                        System.out.println("fail");
                    }
                    break;
                case 9 :
                    return;
                default :
                    System.out.println();
                    System.out.println("-----------------------------------");
                    System.out.println();
            }
        }
    }
}
