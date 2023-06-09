package com.example.finalproject.API;

import com.example.finalproject.Data.DataResponseMovie;
import com.example.finalproject.Data.DataResponseTvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<DataResponseMovie> getMovie(@Query("api_key") String api_key);

    @GET("tv/popular")
    Call<DataResponseTvShow> getTvShow(@Query("api_key") String api_key);
}
