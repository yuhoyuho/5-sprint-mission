package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;

public interface UserService {

    boolean addUser(String password, String name, String email, String city, String street, String zipcode);

    List<User> findAll();

    User findOne(String name, String password);

    boolean updateUserName(String name, String password, String newName);

    boolean updateUserPassword(String name, String password, String newPassword);

    boolean updateUserEmail(String name, String password, String newEmail);

    boolean updateUserAddress(String name, String password, String city, String street, String zipcode);

    boolean deleteUser(String name, String password);
}
