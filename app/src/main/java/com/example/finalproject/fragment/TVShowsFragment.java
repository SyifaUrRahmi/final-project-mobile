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
import com.example.finalproject.Adapter.TvShowAdapter;
import com.example.finalproject.Data_API.DataResponseTvShow;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.Data_API.TvShowResponse;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsFragment extends Fragment {
    ProgressBar pb;
    List<TvShowResponse> tvShowResponses;
    TextView test;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_t_v_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = (SearchView) view.findViewById(R.id.cari_);

        pb = view.findViewById(R.id.pb);
        test = view.findViewById(R.id.test);
        RecyclerView rvTvShow = view.findViewById(R.id.rv_tvshow);
        rvTvShow.setHasFixedSize(true);
        rvTvShow.setLayoutManager(new GridLayoutManager(getActivity(),2));

        rvTvShow.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        Call<DataResponseTvShow> client = ApiConfig.getApiService().getTvShow(ApiConfig.getApiKey());
        client.enqueue(new Callback<DataResponseTvShow>() {
            @Override
            public void onResponse(Call<DataResponseTvShow> call, Response<DataResponseTvShow> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        tvShowResponses = response.body().getTvShow();
                        TvShowAdapter tvShowAdapter = new TvShowAdapter(tvShowResponses);
                        rvTvShow.setAdapter(tvShowAdapter);
                        rvTvShow.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                    }
                    if (response.body() != null) {
                        Log.e("MovieFragment", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponseTvShow> call, Throwable t) {
                pb.setVisibility(View.GONE);
                rvTvShow.setVisibility(View.GONE);
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
                TvShowAdapter adapter = new TvShowAdapter(tvShowResponses);
                newText = newText.toLowerCase();
                ArrayList<TvShowResponse> itemFilter = new ArrayList<>();
                for (TvShowResponse response : tvShowResponses) {
                    String judul = response.getJudul().toLowerCase();
                    if (judul.contains(newText)){
                        itemFilter.add(response);
                        adapter.setFilter(itemFilter);
                    }if (itemFilter.isEmpty()){
                        continue;
                    } else{
                        rvTvShow.setAdapter(adapter);
                    }
                }
            }
        });
    }
}