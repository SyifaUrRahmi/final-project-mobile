package com.example.finalproject.API;

import android.provider.ContactsContract;

import com.example.finalproject.Data.DataResponseMovie;
import com.example.finalproject.Data.DataResponseMovie2;
import com.example.finalproject.Data.DataResponseTvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<DataResponseMovie> getMovie(@Query("api_key") String apiKey);

    @GET("movie/popular/{id}")
    Call<DataResponseMovie2> getMovie2(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<DataResponseTvShow> getTvShow(@Query("api_key") String apiKey);
}
