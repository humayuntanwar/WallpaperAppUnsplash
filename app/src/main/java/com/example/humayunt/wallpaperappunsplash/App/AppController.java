package com.example.humayunt.wallpaperappunsplash.App;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by HumayunT on 7/18/2018.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration= new RealmConfiguration.Builder()
                .name("wallpaper.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
