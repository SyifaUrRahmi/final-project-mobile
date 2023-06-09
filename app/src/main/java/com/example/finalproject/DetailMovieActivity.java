package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView back, poster;
    TextView judul, sinopsis, rating;

    public static final String EXTRA_JUDUL = "extra_judul";
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

        String d_judul = getIntent().getStringExtra(EXTRA_JUDUL);
        judul.setText(d_judul);
    }
}