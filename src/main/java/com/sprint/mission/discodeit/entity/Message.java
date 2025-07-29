package com.sprint.mission.discodeit.entity;

import java.time.LocalTime;
import java.util.UUID;

public class Message {

    private UUID id;
    private final long createdAt;
    private long updatedAt;

    private User user;
    private Channel channel;
    private String content;

    public Message(User user, Channel channel, String content) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = createdAt;
        this.user = user;
        this.channel = channel;
        this.content = content;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getContent() {
        return content;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("user=").append(user.getName());
        sb.append(", channel=").append(channel.getName());
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
