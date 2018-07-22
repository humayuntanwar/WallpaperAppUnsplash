package com.example.humayunt.wallpaperappunsplash.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.humayunt.wallpaperappunsplash.Adapter.PhotosAdapter;
import com.example.humayunt.wallpaperappunsplash.Models.Photo;
import com.example.humayunt.wallpaperappunsplash.R;
import com.example.humayunt.wallpaperappunsplash.Utils.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class FavoriteFragment extends Fragment {
    // Bind Views And Declare Vriables
    @BindView(R.id.fragment_favorite_notification)
    TextView notification;
    @BindView(R.id.fragment_favorite_recyclerview)
    RecyclerView favRecycle;
    private PhotosAdapter photosAdapter;
    private List<Photo> photos = new ArrayList<>();
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container, false);
        unbinder = ButterKnife.bind(this, view);
        //setLayout Manager for Recycler Adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        favRecycle.setLayoutManager(linearLayoutManager);
        photosAdapter = new PhotosAdapter(getActivity(), photos);
        favRecycle.setAdapter(photosAdapter);
        //Get all Photos
        getPhotos();
        return view;
    }

    //method to get all photos from realm Database and Show in Favorites Fragment Using Realm Controller
    private void getPhotos(){
        RealmController realmController = new RealmController();
        photos.addAll(realmController.getPhotos());
        if(photos.size()==0){
            notification.setVisibility(View.VISIBLE);
            favRecycle.setVisibility(View.INVISIBLE);
        }
        else{
            photosAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
