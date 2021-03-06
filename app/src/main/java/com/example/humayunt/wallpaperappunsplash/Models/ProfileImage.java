package com.example.humayunt.wallpaperappunsplash.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class ProfileImage extends RealmObject {
    @SerializedName("small")
    private String small;
    @SerializedName("medium")
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
