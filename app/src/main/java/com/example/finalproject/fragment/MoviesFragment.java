package com.example.finalproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalproject.API.ApiConfig;
import com.example.finalproject.Adapter.MovieAdapter;
import com.example.finalproject.Data_API.DataResponseMovie;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {
    ProgressBar pb;
    List<MovieResponse> movieResponses;
    TextView test;
    SearchView searchView;

    MovieAdapter movieAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = (SearchView) view.findViewById(R.id.cari_);
        searchView.clearFocus();
        pb = view.findViewById(R.id.pb);
        test = view.findViewById(R.id.test);
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
                test.setVisibility(View.VISIBLE);
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }

            private void filterList(String newText) {
                MovieAdapter adapter = new MovieAdapter(movieResponses);
                newText = newText.toLowerCase();
                ArrayList<MovieResponse> itemFilter = new ArrayList<>();
                for (MovieResponse response : movieResponses) {
                    String judul = response.getJudul().toLowerCase();
                    if (judul.contains(newText)){
                        itemFilter.add(response);
                        adapter.setFilter(itemFilter);
                    }if (itemFilter.isEmpty()){
                        continue;
                    } else{
                        rvMovie.setAdapter(adapter);
                    }
                }
            }
        });
    }
}