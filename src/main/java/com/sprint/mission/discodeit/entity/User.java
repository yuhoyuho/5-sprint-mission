package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {

    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private String password;
    private String name;
    private String email;
    private String city;
    private String street;
    private String zipcode;

    public User(String password, String name, String email, String city, String street, String zipcode) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.password = password;
        this.name = name;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
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

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
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

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateCity(String city) {
        this.city = city;
    }

    public void updateStreet(String street) {
        this.street = street;
    }

    public void updateZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("등록된 사용자 = [");
        sb.append("이름 = ").append(name)
        .append(", 이메일 = ").append(email)
        .append(", 시 = ").append(city)
        .append(", 구 = ").append(street)
        .append(", 동 = ").append(zipcode)
        .append(']');
        return sb.toString();
    }
}
