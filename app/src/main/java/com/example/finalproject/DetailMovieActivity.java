package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.API.ApiConfig;
import com.example.finalproject.Data.DataResponseMovie2;
import com.example.finalproject.Data.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String did = getIntent().getStringExtra(EXTRA_ID);

        Call<DataResponseMovie2> client = ApiConfig.getApiService().getMovie2(did);
        client.enqueue(new Callback<DataResponseMovie2>() {
            @Override
            public void onResponse(Call<DataResponseMovie2> call, Response<DataResponseMovie2> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MovieResponse movieResponse = response.body().getMovie();
                        System.out.println("test"+movieResponse);
                        judul.setText(movieResponse.getJudul());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponseMovie2> call, Throwable t) {

            }
        });
    }
}