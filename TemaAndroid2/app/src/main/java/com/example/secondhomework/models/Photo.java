package com.example.secondhomework.models;

public class Photo {
    private String photoTitle;
    private String photoUrl;
    private String thumbnailUrl;

    public Photo(String photoTitle, String photoUrl, String thumbnailUrl) {
        this.photoTitle = photoTitle;
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
