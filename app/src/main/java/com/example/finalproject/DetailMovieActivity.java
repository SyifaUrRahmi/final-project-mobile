package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.API.ApiConfig;
import com.example.finalproject.Data.DataResponseMovie2;

import retrofit2.Call;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView back, poster;
    TextView judul, sinopsis, rating;

    public static final String EXTRA_ID = "extra_id";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        back = findViewById(R.id.back);
        poster = findViewById(R.id.poster);
        judul = findViewById(R.id.judul);
        sinopsis = findViewById(R.id.sinopsis);
        rating = findViewById(R.id.rating);

        Call<DataResponseMovie2> client = ApiConfig.getApiService().getMovie2(ApiConfig.getApiKey());
    }
}