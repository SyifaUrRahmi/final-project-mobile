package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.Database.DatabaseContract;
import com.example.finalproject.Database.DatabaseHelper;
import com.example.finalproject.Database.Detail;
import com.example.finalproject.Database.DetailHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ImageView back, poster, jenis, kembali, fav;
    TextView judul, sinopsis, vote, release;
    private boolean isFavorit = false;
    public static final String EXTRA_JENIS = "extra_jenis";
    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_RELEASE = "extra_release";
    public static final String EXTRA_VOTE = "extra_vote";
    public static final String EXTRA_OVERVIEW = "extra_overview";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_BACK = "extra_back";

    private DetailHelper detailHelper;
    private Detail detail;

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
        kembali = findViewById(R.id.kembali);
        jenis = findViewById(R.id.jenis);
        fav = findViewById(R.id.fav);

        String d_jenis = getIntent().getStringExtra(EXTRA_JENIS);
        String d_judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String d_sinopsis = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String d_back = getIntent().getStringExtra(EXTRA_BACK);
        String d_poster = getIntent().getStringExtra(EXTRA_POSTER);
        String d_vote = getIntent().getStringExtra(EXTRA_VOTE);
        String d_release = getIntent().getStringExtra(EXTRA_RELEASE);

//        boolean hasil = DetailHelper.cari(String.valueOf(d_judul));
//        isFavorit = hasil;

        detailHelper = DetailHelper.getInstance(getApplicationContext());
        detailHelper.open();

        if (detail == null) {
            detail = new Detail();
        }

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
        double vote_ = Double.parseDouble(String.valueOf(d_vote));
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
        kembali.setOnClickListener(view -> {
            finish();
        });
        fav.setOnClickListener(view -> {
            isFavorit = !isFavorit;
            if (isFavorit == true){
                if (d_jenis.equals("movie")){
                    detail.setJenis("movie");
                }else {
                    detail.setJenis("tvshow");
                }

                detail.setJudul(d_judul);
                detail.setSinopsis(d_sinopsis);
                detail.setWaktu(d_release);
                detail.setPoster(d_poster);
                detail.setBackdrop(d_back);
                detail.setVote(d_vote);

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.DetailColumns.JENIS, d_jenis);
                values.put(DatabaseContract.DetailColumns.JUDUL, d_judul);
                values.put(DatabaseContract.DetailColumns.WAKTU, d_release);
                values.put(DatabaseContract.DetailColumns.SINOPSIS, d_sinopsis);
                values.put(DatabaseContract.DetailColumns.POSTER, d_poster);
                values.put(DatabaseContract.DetailColumns.BACKDROP, d_back);
                values.put(String.valueOf(DatabaseContract.DetailColumns.VOTE), d_vote);

                long result = detailHelper.insert(values);
                if (result > 0) {
                    detail.setId((int) result);
                    Toast.makeText(this, "Berhasil Menambahkan " + d_judul +  " ke Favorite", Toast.LENGTH_SHORT).show();
                    fav.setImageDrawable(getResources().getDrawable(R.drawable.baseline_favorite_24));
                } else {
                    Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
                }
            }else {
                hapus();
            }
        });
    }

    private void hapus() {
        long result = detailHelper.deleteById(String.valueOf(detail.getId()));
        if (result > 0) {
            String d_judul = getIntent().getStringExtra(EXTRA_JUDUL);
            Toast.makeText(this, "Berhasil Menghapus "+ d_judul + " dari Favorit", Toast.LENGTH_SHORT).show();
            fav.setImageDrawable(getResources().getDrawable(R.drawable.baseline_favorite_border_24));
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}