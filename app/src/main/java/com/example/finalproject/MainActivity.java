package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.finalproject.fragment.FavoritesFragment;
import com.example.finalproject.fragment.MoviesFragment;
import com.example.finalproject.fragment.TVShowsFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = findViewById(R.id.tb);
        tb.setTitle("Movies");

        FragmentManager fragmentManager = getSupportFragmentManager();
        MoviesFragment moviesFragment = new MoviesFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(MoviesFragment.class.getSimpleName());

        if (!(fragment instanceof MoviesFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, moviesFragment,
                            MoviesFragment.class.getSimpleName())
                    .commit();
        }
        LinearLayout mv = findViewById(R.id.movies);
        mv.setOnClickListener(movies_ -> {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, moviesFragment,
                            MoviesFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
        LinearLayout ts = findViewById(R.id.ts);
        ts.setOnClickListener(tvshows_ -> {
            tb.setTitle("TV Shows");
            TVShowsFragment tvShowsFragment = new TVShowsFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, tvShowsFragment,
                            TVShowsFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
        LinearLayout fav = findViewById(R.id.fav);
        fav.setOnClickListener(fav_ -> {
            tb.setTitle("Favorites");
            FavoritesFragment favoritesFragment = new FavoritesFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, favoritesFragment,
                            FavoritesFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }
}