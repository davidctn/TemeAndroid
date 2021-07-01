package com.example.secondhomework.models;

public class User {
    private String id;
    private String username;
    private String email;

    private String postTitle;
    private String postBody;

    public User(String username, String email,String id) {
        this.id = id;
        this.username = username;
        this.email = email;
        postTitle="";
        postBody="";
    }

    public User(String id, String username, String email, String postTitle, String postBody) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

}
