package com.example.finalproject.Data_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponseMovie {
    @SerializedName("results")
    private List<MovieResponse> movie;

    public List<MovieResponse> getmovie() {
        return movie;
    }
}
