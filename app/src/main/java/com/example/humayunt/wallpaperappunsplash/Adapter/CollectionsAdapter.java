package com.example.humayunt.wallpaperappunsplash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.example.humayunt.wallpaperappunsplash.Models.Collection;
import com.example.humayunt.wallpaperappunsplash.R;
import com.example.humayunt.wallpaperappunsplash.Utils.GlideApp;
import com.example.humayunt.wallpaperappunsplash.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HumayunT on 7/17/2018.
 */

public class CollectionsAdapter extends BaseAdapter {
    private Context context;
    private List<Collection> collections;

    public CollectionsAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collections.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_collection,parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        ButterKnife.bind(this,view);
        Collection collection = collections.get(position);
        if(collection.getTitle()!=null){
            holder.title.setText(collection.getTitle());
        }
        holder.totalphotos.setText(String.valueOf(collection.getTotalphotos()) + "photos");
        GlideApp
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(holder.collectionPhoto);
        return view;


    }
    static class ViewHolder {
        @BindView(R.id.item_collection_title)
        TextView title;
        @BindView(R.id.item_collection_photo)
        SquareImage collectionPhoto;
        @BindView(R.id.item_collection_total_photos)
        TextView totalphotos;
        public ViewHolder (View view){
            ButterKnife.bind(this,view);
        }
    }
}
