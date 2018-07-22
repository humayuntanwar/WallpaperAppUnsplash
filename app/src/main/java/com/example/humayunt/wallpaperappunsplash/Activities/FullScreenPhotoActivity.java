package com.example.humayunt.wallpaperappunsplash.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.humayunt.wallpaperappunsplash.Models.Photo;
import com.example.humayunt.wallpaperappunsplash.R;
import com.example.humayunt.wallpaperappunsplash.Utils.Functions;
import com.example.humayunt.wallpaperappunsplash.Utils.GlideApp;
import com.example.humayunt.wallpaperappunsplash.Utils.RealmController;
import com.example.humayunt.wallpaperappunsplash.WebService.ApiInterface;
import com.example.humayunt.wallpaperappunsplash.WebService.ServiceGenerator;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HumayunT on 7/18/2018.
 */

public class FullScreenPhotoActivity extends AppCompatActivity {
    //Bind Views using BUtterKnife
    @BindView(R.id.activity_fullscreen_photo_photo)
    ImageView fullscreenphoto;
    @BindView(R.id.activity_fullscreen_photo_username)
    TextView username;
    @BindView(R.id._activity_fullscreen_photo_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.activity_fullscreen_photo_fab_menu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.activity_fullscreen_photo_fab_wallpaper)
    FloatingActionButton wallpaper;
    @BindView(R.id.activity_fullscreen_photo_fab_favorite)
    FloatingActionButton favorite;
    @BindDrawable(R.drawable.ic_fab_favorite)
    Drawable icfav;
    @BindDrawable(R.drawable.ic_check_favorited)
    Drawable icfavorited;
    private Unbinder unbinder;
    private Bitmap photoBitmap;
    private RealmController realmController;
    private Photo photo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        unbinder = ButterKnife.bind(this);
        //getting Photos Id
        Intent intent= getIntent();
        String photoId = intent.getStringExtra("photoId");
        getPhoto(photoId);
        //RealmController checks it photos exist in realm database
        realmController = new RealmController();
        if(realmController.isPhotoExist(photoId)){
            favorite.setImageDrawable(icfavorited);

        }
    }
    //This GetPhotos Method uses the id passed from click to get the Image by passing the Id to API and Get Photo from Id.
    private void getPhoto(String id ){
        //create Service
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<Photo> call = apiInterface.getPhoto(id);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                //Pass photo to main
                photo = response.body();
                updateUI(photo);
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }
    //Update UI
    private  void updateUI(Photo photo){
        try{
            //User Name
            username.setText(photo.getUser().getUsername());
            //Glide for User Profile Image
            GlideApp.with(FullScreenPhotoActivity.this)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(userAvatar);
            //Glide For Full Screen Image
            GlideApp.with(FullScreenPhotoActivity.this)
                    .asBitmap()
                    .load(photo.getUrl().getRegular())
                    .centerCrop()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            fullscreenphoto.setImageBitmap(resource);
                            photoBitmap = resource;
                        }
                    });

        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
    //Saving Photos as Favorite after usier clicks Favorite photos from floating Menu
    @OnClick(R.id.activity_fullscreen_photo_fab_favorite)
    public void setFabFavorite(){
        if(realmController.isPhotoExist(photo.getId())) {
            //if photo exist delete it
            realmController.deletePhoto(photo);
            //fill fav icon red
            favorite.setImageDrawable(icfav);
            Toast.makeText(this, "Remove Favorite", Toast.LENGTH_LONG).show();
        }else {
            //Save Photo to realm
            realmController.savePhoto(photo);
            //setIcon
            favorite.setImageDrawable(icfavorited);
            Toast.makeText(this, "Favorited", Toast.LENGTH_LONG).show();
        }
        //close Menu
            fabMenu.close(true);
    }
    //Setting Wallpaper of HomeScreen using Fab button
    @OnClick(R.id.activity_fullscreen_photo_fab_wallpaper)
    public  void setFabWallpaper(){
        if(photoBitmap!= null){
            //Set wallpaper
            if(Functions.setWallpaper(this, photoBitmap)){
                Toast.makeText(this, "WALLPAPER SET SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "WALLPAPER SET FAIL",Toast.LENGTH_LONG).show();

            }
        }
        //close Menu
        fabMenu.close(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
