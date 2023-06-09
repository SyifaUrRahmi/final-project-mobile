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
import com.example.finalproject.Adapter.TvShowAdapter;
import com.example.finalproject.Data_API.DataResponseTvShow;
import com.example.finalproject.Data_API.TvShowResponse;
import com.example.finalproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsFragment extends Fragment {
    ProgressBar pb;
    List<TvShowResponse> tvShowResponses;
    LinearLayout ly_tes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pb = view.findViewById(R.id.pb);
        ly_tes = view.findViewById(R.id.test);
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
                ly_tes.setVisibility(View.VISIBLE);
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}