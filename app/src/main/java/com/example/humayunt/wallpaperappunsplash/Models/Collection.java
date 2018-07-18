package com.example.humayunt.wallpaperappunsplash.Models;

import com.google.gson.annotations.SerializedName;

import butterknife.Unbinder;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class Collection {
    @SerializedName("id")
    private  int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("total_photos")
    private int totalphotos;
    @SerializedName("cover_photo")
    private Photo coverPhoto = new Photo();
    @SerializedName("user")
    private User user = new User();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalphotos() {
        return totalphotos;
    }

    public void setTotalphotos(int totalphotos) {
        this.totalphotos = totalphotos;
    }

    public Photo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
