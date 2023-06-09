package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailTvShowActivity extends AppCompatActivity {
    ImageView back, poster;
    TextView judul, sinopsis, vote, release;

    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_RELEASE = "extra_release";
    public static final String EXTRA_VOTE = "extra_vote";
    public static final String EXTRA_OVERVIEW = "extra_overview";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_BACK = "extra_back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        back = findViewById(R.id.back);
        poster = findViewById(R.id.poster);
        judul = findViewById(R.id.judul);
        sinopsis = findViewById(R.id.sinopsis);
        vote = findViewById(R.id.rating);
        release = findViewById(R.id.tanggal);

        String d_judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String d_sinopsis = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String d_back = getIntent().getStringExtra(EXTRA_BACK);
        String d_poster = getIntent().getStringExtra(EXTRA_POSTER);
        String d_vote = getIntent().getStringExtra(EXTRA_VOTE);
        String d_release = getIntent().getStringExtra(EXTRA_RELEASE);
        judul.setText(d_judul);
        sinopsis.setText(d_sinopsis);
        vote.setText(d_vote);
        release.setText(d_release);
        Glide.with(DetailTvShowActivity.this)
                .load(d_back)
                .into(back);
        Glide.with(DetailTvShowActivity.this)
                .load(d_poster)
                .into(poster);

    }
}