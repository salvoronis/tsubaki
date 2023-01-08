package com.tsubaki.dto;

public class UserDto {

    private long id;

    private String username;

    private String email;

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserDto(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
