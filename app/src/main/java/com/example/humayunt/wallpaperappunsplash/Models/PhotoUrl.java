package com.example.humayunt.wallpaperappunsplash.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class PhotoUrl extends RealmObject {
    @SerializedName("full")
    private  String full;
    @SerializedName("regular")
    private String regular;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
