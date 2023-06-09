package com.example.finalproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.finalproject.API.ApiConfig;
import com.example.finalproject.Adapter.MovieAdapter;
import com.example.finalproject.Data_API.DataResponseMovie;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {
    ProgressBar pb;
    List<MovieResponse> movieResponses;
    LinearLayout ly_tes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pb = view.findViewById(R.id.pb);
        ly_tes = view.findViewById(R.id.test);
        RecyclerView rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new GridLayoutManager(getActivity(),2));

        rvMovie.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        Call<DataResponseMovie> client = ApiConfig.getApiService().getMovie(ApiConfig.getApiKey());
        client.enqueue(new Callback<DataResponseMovie>() {
            @Override
            public void onResponse(Call<DataResponseMovie> call, Response<DataResponseMovie> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        movieResponses = response.body().getmovie();
                        MovieAdapter adapter = new MovieAdapter(movieResponses);
                        rvMovie.setAdapter(adapter);
                        rvMovie.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                    }
                    if (response.body() != null) {
                        Log.e("MovieFragment", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponseMovie> call, Throwable t) {
                pb.setVisibility(View.GONE);
                rvMovie.setVisibility(View.GONE);
                ly_tes.setVisibility(View.VISIBLE);
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}