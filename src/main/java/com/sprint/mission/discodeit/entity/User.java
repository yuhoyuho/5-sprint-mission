package com.sprint.mission.discodeit.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private final long createdAt;
    private long updatedAt;

    private String name;
    private String email;

    private List<Channel> channels = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = createdAt;

        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", channels=").append(channels);
        sb.append(", messages=").append(messages);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void updateUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.updatedAt = LocalTime.now().toSecondOfDay();
        this.channels = user.getChannels();
        this.messages = user.getMessages();
    }
}
