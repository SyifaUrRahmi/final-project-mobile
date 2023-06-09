package com.example.finalproject.API;

import android.provider.ContactsContract;

import com.example.finalproject.Data.DataResponseMovie;
import com.example.finalproject.Data.DataResponseMovie2;
import com.example.finalproject.Data.DataResponseTvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<DataResponseMovie> getMovie(@Query("api_key") String apiKey);

    @GET("movie/popular/{id}?api_key=10839ceb0902ee2fa60298ef58019e86")
    Call<DataResponseMovie2> getMovie2(@Path("id") String id);

    @GET("tv/popular")
    Call<DataResponseTvShow> getTvShow(@Query("api_key") String apiKey);
}
