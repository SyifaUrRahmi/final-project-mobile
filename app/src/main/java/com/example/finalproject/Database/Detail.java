package com.example.finalproject.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Detail implements Parcelable {
    private String id_, judul, waktu, sinopsis, poster, backdrop, jenis, vote;
//    private Number vote;
    private int id;

    public Detail() {

    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public Detail(int id, String id_, String jenis, String judul, String waktu, String sinopsis, String poster, String backdrop, String vote) {
        this.id = id;
        this.id_ = id_;
        this.judul = judul;
        this.waktu = waktu;
        this.sinopsis = sinopsis;
        this.poster = poster;
        this.backdrop = backdrop;
        this.jenis = jenis;
        this.vote = vote;
    }

    protected Detail(Parcel in) {
        id = in.readInt();
        id_ = in.readString();
        judul = in.readString();
        waktu = in.readString();
        sinopsis = in.readString();
        poster = in.readString();
        backdrop = in.readString();
        jenis = in.readString();
        vote = in.readString();
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

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(id_);
        parcel.writeString(judul);
        parcel.writeString(waktu);
        parcel.writeString(sinopsis);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
        parcel.writeString(jenis);
        parcel.writeString(vote);
    }
}
