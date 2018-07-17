package com.example.humayunt.wallpaperappunsplash.WebService;

import com.example.humayunt.wallpaperappunsplash.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HumayunT on 7/17/2018.
 */

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}
