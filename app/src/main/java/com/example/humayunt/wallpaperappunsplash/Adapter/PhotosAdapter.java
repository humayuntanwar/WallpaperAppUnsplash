package com.example.humayunt.wallpaperappunsplash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.humayunt.wallpaperappunsplash.Activities.FullScreenPhotoActivity;
import com.example.humayunt.wallpaperappunsplash.Models.Photo;
import com.example.humayunt.wallpaperappunsplash.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.example.humayunt.wallpaperappunsplash.Utils.GlideApp;
import com.example.humayunt.wallpaperappunsplash.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder>{
    private final String TAG = PhotosAdapter.class.getSimpleName();

    private Context context;
    private List<Photo> photos;

    public PhotosAdapter(Context context, List<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.username.setText(photo.getUser().getUsername());
        GlideApp.with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(holder.photo);
        GlideApp.with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(holder.userAvatar);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        @BindView(R.id.item_photo_user_avatar)
        CircleImageView userAvatar;
        @BindView(R.id.item_photo_username)
        TextView username;
        @BindView(R.id.item_photo_photo)
        SquareImage photo;
        @BindView(R.id.item_photo_layout)
        FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_photo_layout)
        public void handleOnClick() {
            Log.d(TAG, "dmmmmmmmmm");
            int position = getAdapterPosition();
            String photoId = photos.get(position).getId();
            Intent intent = new Intent(context, FullScreenPhotoActivity.class);
            intent.putExtra("photoId", photoId);
            context.startActivity(intent);
        }
    }
}
