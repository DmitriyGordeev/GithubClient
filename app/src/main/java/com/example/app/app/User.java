package com.example.app.app;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private int followers;

    public User(String name, String avatarUrl, int followers) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
    }

}
