package com.example.humayunt.wallpaperappunsplash.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.humayunt.wallpaperappunsplash.WebService.ApiInterface;
import com.example.humayunt.wallpaperappunsplash.WebService.ServiceGenerator;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

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
     private Unbinder unbinder;
    private  Bitmap photoBitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        unbinder = ButterKnife.bind(this);
        Intent intent= getIntent();
        String photoId = intent.getStringExtra("photoId");
        getPhoto(photoId);
    }
    private void getPhoto(String id ){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<Photo> call = apiInterface.getPhoto(id);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                Photo photo = response.body();
                updateUI(photo);
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }
    private  void updateUI(Photo photo){
        try{
            username.setText(photo.getUser().getUsername());
            GlideApp.with(FullScreenPhotoActivity.this)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(userAvatar);
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
    @OnClick(R.id.activity_fullscreen_photo_fab_favorite)
    public void setFabFavorite(){
        fabMenu.close(true);
    }
    @OnClick(R.id.activity_fullscreen_photo_fab_wallpaper)
    public  void setFabWallpaper(){
        if(photoBitmap!= null){
            if(Functions.setWallpaper(this, photoBitmap)){
                Toast.makeText(this, "WALLPAPER SET SUCCESSFULLY",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "WALLPAPER SET FAIL",Toast.LENGTH_LONG).show();

            }
        }
        fabMenu.close(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
