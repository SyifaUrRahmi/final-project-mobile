package com.example.finalproject.Data;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String judul;

    @SerializedName("release_date")
    private String waktu;

    @SerializedName("vote_average")
    private String vote;

    @SerializedName("overview")
    private String sinopsis;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backdrop;

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getVote() {
        return vote;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }
}
