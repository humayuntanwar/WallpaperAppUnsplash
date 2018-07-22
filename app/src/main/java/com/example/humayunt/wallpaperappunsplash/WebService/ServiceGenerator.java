package com.example.humayunt.wallpaperappunsplash.WebService;

import com.example.humayunt.wallpaperappunsplash.Utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HumayunT on 7/17/2018.
 * This class provides the connection between unsplashed APi and our Ap using OkHttp and retrofit and GSON
 */

public class ServiceGenerator {
    //retrofit obj
    private static Retrofit retrofit = null;
    //gson builder obj
    private  static Gson gson = new GsonBuilder().create();
    //HTTP Log interseptor for checking logs during calls
    private static HttpLoggingInterceptor httpLoggingInterceptor =  new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //okhttp client obj
    private static OkHttpClient.Builder okHttpClientBuilder =  new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    //Request chain
                    Request request = chain.request().newBuilder()
                            //api using authorization and passing our client ID
                            .addHeader("Authorization", "Client-ID " + Constants.APPLICATION_ID)
                            .build();
                    return chain.proceed(request);
                }
            });

    //okhttpclient build
    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();
    //creating Service
    public static <T> T createService(Class<T> serviceClass){
        if(retrofit== null){
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    //Pass Base URL of Unspalsed APi
                    .baseUrl(Constants.BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(serviceClass);
    }
}

