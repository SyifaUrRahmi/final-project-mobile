package com.example.finalproject.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Detail implements Parcelable {
    private String judul, waktu, sinopsis, poster, backdrop, jenis;
    private Number vote;

    public Detail(int id, String judul, String waktu, String sinopsis, String poster, String backdrop, String jenis, Number vote) {
        this.judul = judul;
        this.waktu = waktu;
        this.sinopsis = sinopsis;
        this.poster = poster;
        this.backdrop = backdrop;
        this.jenis = jenis;
        this.vote = vote;
    }

    protected Detail(Parcel in) {
        judul = in.readString();
        waktu = in.readString();
        sinopsis = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        jenis = in.readString();
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public Number getVote() {
        return vote;
    }

    public void setVote(Number vote) {
        this.vote = vote;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(judul);
        parcel.writeString(waktu);
        parcel.writeString(sinopsis);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
        parcel.writeString(jenis);
    }
}
