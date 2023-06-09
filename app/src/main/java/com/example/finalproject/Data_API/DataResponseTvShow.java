package com.example.finalproject.Data_API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponseTvShow {
    @SerializedName("results")
    private List<TvShowResponse> tvShow;

    public List<TvShowResponse> getTvShow() {
        return tvShow;
    }
}
