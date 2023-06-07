package com.example.finalproject.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieResponse implements Parcelable {
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

    protected MovieResponse(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        waktu = in.readString();
        sinopsis = in.readString();
        poster = in.readString();
        backdrop = in.readString();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(judul);
        parcel.writeString(waktu);
        parcel.writeString(sinopsis);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
    }
}
