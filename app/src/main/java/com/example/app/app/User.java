package com.example.app.app;

import com.google.gson.annotations.SerializedName;

public class User {

    private String name;
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private int followers;

    public User(String login, String name, String avatarUrl, int followers) {
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
    }

    public String getLogin() { return login; }
    public String getName() { return name; }
    public String getAvatarUrl() { return avatarUrl; }
    public int getFollowers() { return followers; }

}
