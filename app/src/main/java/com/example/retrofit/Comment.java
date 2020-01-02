package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    @SerializedName("body")
    private String text;

    public Comment(Integer postId, Integer id, String name, String email, String text) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
