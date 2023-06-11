package com.example.finalproject.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.DetailActivity;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResponse> movieResponses;

    public MovieAdapter(List<MovieResponse> movieResponses){
        this.movieResponses = movieResponses;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_tvshow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResponse movieResponse = movieResponses.get(position);
        String id_ = String.valueOf(movieResponse.getId());
        String jenis = "movie";
        String judul = movieResponse.getJudul();
        String release = movieResponse.getWaktu();
        String overview = movieResponse.getSinopsis();
        String vote = String.valueOf(movieResponse.getVote());
        String backdrop = "https://image.tmdb.org/t/p/w500"+movieResponse.getBackdrop();
        String[] tahun = release.split("-");
        holder.tv_judul.setText(movieResponse.getJudul());
        holder.tv_tahun.setText(tahun[0]);
        String poster_path = "https://image.tmdb.org/t/p/w500"+movieResponse.getPoster();
        Glide.with(holder.itemView.getContext())
                .load(poster_path)
                .apply(new RequestOptions().override(350,
                        550))
                .into(holder.iv_gambar);
        holder.ly_movie_tvshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
                toDetail.putExtra(DetailActivity.EXTRA_JENIS, jenis);
                toDetail.putExtra(DetailActivity.EXTRA_JUDUL, judul);
                toDetail.putExtra(DetailActivity.EXTRA_BACK, backdrop);
                toDetail.putExtra(DetailActivity.EXTRA_OVERVIEW, overview);
                toDetail.putExtra(DetailActivity.EXTRA_RELEASE, release);
                toDetail.putExtra(DetailActivity.EXTRA_POSTER, poster_path);
                toDetail.putExtra(DetailActivity.EXTRA_VOTE, vote);
                toDetail.putExtra(DetailActivity.EXTRA_ID_, id_);
                holder.itemView.getContext().startActivity(toDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_gambar;
        TextView tv_judul, tv_tahun;
        LinearLayout ly_movie_tvshow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_tahun = itemView.findViewById(R.id.tv_tahun);
            ly_movie_tvshow = itemView.findViewById(R.id.movie_tvshow);
        }
    }
    public void setFilter(ArrayList<MovieResponse> filter){
        this.movieResponses = filter;
        notifyDataSetChanged();
    }
}
