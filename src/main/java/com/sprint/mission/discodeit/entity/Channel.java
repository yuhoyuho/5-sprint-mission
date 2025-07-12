package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Channel {

    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private String name;
    private String description;
    
    public Channel() {
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }
    
    public Channel(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Channel = [");
        sb.append("name =").append(name)
        .append(", description = ").append(description)
        .append(']');
        return sb.toString();
    }
}
