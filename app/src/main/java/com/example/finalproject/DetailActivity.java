package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {
    ImageView back, poster, jenis;
    TextView judul, sinopsis, vote, release;

    public static final String EXTRA_JENIS = "extra_jenis";
    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_RELEASE = "extra_release";
    public static final String EXTRA_VOTE = "extra_vote";
    public static final String EXTRA_OVERVIEW = "extra_overview";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_BACK = "extra_back";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back);
        poster = findViewById(R.id.poster);
        judul = findViewById(R.id.judul);
        sinopsis = findViewById(R.id.sinopsis);
        vote = findViewById(R.id.rating);
        release = findViewById(R.id.tanggal);

        jenis = findViewById(R.id.jenis);

        String d_jenis = getIntent().getStringExtra(EXTRA_JENIS);
        String d_judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String d_sinopsis = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String d_back = getIntent().getStringExtra(EXTRA_BACK);
        String d_poster = getIntent().getStringExtra(EXTRA_POSTER);
        String d_vote = getIntent().getStringExtra(EXTRA_VOTE);
        String d_release = getIntent().getStringExtra(EXTRA_RELEASE);

        SimpleDateFormat awal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat akhir = new SimpleDateFormat("MMMM dd, yyyy");

        if (d_jenis.equals("movie")){
            jenis.setImageDrawable(getResources().getDrawable(R.drawable.baseline_movie_creation_24));
        }else {
            jenis.setImageDrawable(getResources().getDrawable(R.drawable.baseline_tv_24));
        }
        judul.setText(d_judul);
        if (d_sinopsis.length() > 0){
            sinopsis.setText(d_sinopsis);
        }else {
            sinopsis.setText("-");
        }
        double vote_ = Double.parseDouble(d_vote);
        vote.setText(String.valueOf(vote_));
        try {
            release.setText(akhir.format(awal.parse(d_release)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Glide.with(DetailActivity.this)
                .load(d_back)
                .into(back);
        Glide.with(DetailActivity.this)
                .load(d_poster)
                .into(poster);

    }
}