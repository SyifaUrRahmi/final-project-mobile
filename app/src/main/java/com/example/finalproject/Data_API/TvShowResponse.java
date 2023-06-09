package com.example.finalproject.Data_API;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShowResponse implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String judul;

    @SerializedName("first_air_date")
    private String waktu;

    @SerializedName("vote_average")
    private Number vote;

    @SerializedName("overview")
    private String sinopsis;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backdrop;

    protected TvShowResponse(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        waktu = in.readString();
        if (in.readByte() == 0) {
            vote = null;
        } else {
            vote = in.readInt();
        }
        sinopsis = in.readString();
        poster = in.readString();
        backdrop = in.readString();
    }

    public static final Creator<TvShowResponse> CREATOR = new Creator<TvShowResponse>() {
        @Override
        public TvShowResponse createFromParcel(Parcel in) {
            return new TvShowResponse(in);
        }

        @Override
        public TvShowResponse[] newArray(int size) {
            return new TvShowResponse[size];
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
        if (vote == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
        }
        parcel.writeString(sinopsis);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
    }
}
