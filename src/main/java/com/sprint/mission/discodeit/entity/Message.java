package com.sprint.mission.discodeit.entity;

import java.util.List;
import java.util.UUID;

public class Message {

    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private String username;
    private List<String> content;
    
    public Message() {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }
    
    public Message(String username, List<String> content) {
        this.username = username;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getContent() {
        return content;
    }

    public void updateId(UUID id) {
        this.id = id;
    }

    public void updateCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void updateUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateContent(List<String> content) {
        this.content = content;
    }
}
