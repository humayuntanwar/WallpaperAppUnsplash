package com.example.humayunt.wallpaperappunsplash.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HumayunT on 7/17/2018.
 * This is The Photo Model Class used to get Detials of photos from Unsplash Api return calls
 */

public class Photo extends RealmObject {

    //Using serialization from GSON to match ids to Unsplash Api
    @SerializedName("id")
    //id is primarykey
    @PrimaryKey
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("urls")
    private PhotoUrl url = new PhotoUrl();
    @SerializedName("user")
    private  User user = new User();

    //Get and Set Methods for Photos.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public void setUrl(PhotoUrl url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
