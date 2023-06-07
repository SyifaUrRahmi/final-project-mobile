package com.example.finalproject.API;

import android.provider.ContactsContract;

import com.example.finalproject.Data.DataResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<DataResponseMovie> getMovie(@Query("api_key") String apiKey);
}
