package com.example.noteapp.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-mm-dd hh:mm:ss")
            .create();

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder().addInterceptor(logging);

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://noteappteam20.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClientBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("api/user")
    Call<List<User>> getUser();
}
