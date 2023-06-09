package com.example.finalproject.Data;

import com.google.gson.annotations.SerializedName;

public class DataResponseMovie2 {
    @SerializedName("results")
    private MovieResponse movie;

    public MovieResponse getMovie(){
        return movie;
    }
}
