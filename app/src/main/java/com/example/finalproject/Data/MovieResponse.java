package com.example.finalproject.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieResponse{
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String judul;

    @SerializedName("release_date")
    private String waktu;

    @SerializedName("vote_average")
    private Number vote;

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

    public Number getVote() {
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
