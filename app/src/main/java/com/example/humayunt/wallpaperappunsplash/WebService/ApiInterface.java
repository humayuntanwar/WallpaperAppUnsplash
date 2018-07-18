package com.example.humayunt.wallpaperappunsplash.WebService;

import com.example.humayunt.wallpaperappunsplash.Models.Collection;
import com.example.humayunt.wallpaperappunsplash.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HumayunT on 7/17/2018.
 */

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos();
    @GET("collections/featured")
    Call<List<Collection>> getCollections();
    @GET("collecctions/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);
    @GET("collections/{id}/photos")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id);


}
