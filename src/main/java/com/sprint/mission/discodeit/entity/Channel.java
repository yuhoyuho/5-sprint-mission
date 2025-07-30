package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Channel implements Serializable {

    private UUID id;
    private final long createdAt;
    private long updatedAt;

    private String name;
    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public Channel(String name) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = createdAt;

        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void updateChannel(Channel channel) {
        this.name = channel.getName();
        this.updatedAt = System.currentTimeMillis();
        this.messages = channel.getMessages();
        this.users = channel.getUsers();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Channel{");
        sb.append("name='").append(name).append('\'');
//        sb.append(", users=").append(users);
//        -> 이 부분을 주석 처리 하지 않으면 user.toString과 channel.toString이 순환 참조되어 스택 오버플로우 발생
        sb.append(", messages=").append(messages);
        sb.append('}');
        return sb.toString();
    }
}
