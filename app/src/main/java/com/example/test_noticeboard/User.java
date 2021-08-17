package com.example.test_noticeboard;

public class User {
    private String nickname;

    public User() {
        this.nickname = "";
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
