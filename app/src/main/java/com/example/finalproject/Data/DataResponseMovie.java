package com.example.finalproject.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponseMovie {
    @SerializedName("result")
    private List<MovieResponse> movie;

    public List<MovieResponse> getmovie() {
        return movie;
    }
}
