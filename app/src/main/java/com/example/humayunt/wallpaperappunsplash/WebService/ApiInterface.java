package com.example.humayunt.wallpaperappunsplash.WebService;

import com.example.humayunt.wallpaperappunsplash.Models.Collection;
import com.example.humayunt.wallpaperappunsplash.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HumayunT on 7/17/2018.
 * API Interface Using GET from RetroFIr to manage Api Urls and APi call Needed in the Application
 */

public interface ApiInterface {
    //Get all photos
    @GET("photos")
    Call<List<Photo>> getPhotos();
    //get Collections
    @GET("collections/featured")
    Call<List<Collection>> getCollections();
    //Get collection by id to retrieve information about who made collection
    @GET("collecctions/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);
    //Get Photos by collection id
    @GET("collections/{id}/photos")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);
    //get Photos by Id Individual
    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id);


}
