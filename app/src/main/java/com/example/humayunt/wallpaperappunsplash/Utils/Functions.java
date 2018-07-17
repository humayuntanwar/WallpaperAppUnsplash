package com.example.humayunt.wallpaperappunsplash.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.example.humayunt.wallpaperappunsplash.R;


/**
 * Created by HumayunT on 7/17/2018.
 */

public class Functions {
    public static  void  changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,fragment)
                .commit();

    }
}
