package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {

    private final List<User> list = new ArrayList<>();

    @Override
    public boolean addUser(String password, String name, String email, String city, String street, String zipcode) {
        User user = new User(password, name, email, city, street, zipcode);
        boolean flag = isExisted(name, password);

        if(flag) {
            list.add(user);
            return true;
        }

        return false;
    }

    @Override
    public List<User> findAll() {
        return list;
    }

    @Override
    public User findOne(String name, String password) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean updateUserName(String name, String password, String newName) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                user.updateName(newName);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateUserPassword(String name, String password, String newPassword) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                user.updatePassword(newPassword);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateUserEmail(String name, String password, String newEmail) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                user.updateEmail(newEmail);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateUserAddress(String name, String password, String city, String street, String zipcode) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                user.updateCity(city);
                user.updateStreet(street);
                user.updateZipcode(zipcode);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteUser(String name, String password) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                list.remove(user);
                return true;
            }
        }

        return false;
    }

    public boolean isExisted(String name, String password) {
        for(User user : list) {
            if(user.getName().equals(name) && user.getPassword().equals(password)) {
                return false;
            }
        }

        return true;
    }
}
