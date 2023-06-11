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
import com.example.finalproject.Data_API.TvShowResponse;
import com.example.finalproject.DetailActivity;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private List<TvShowResponse> tvShowResponses;

    public TvShowAdapter(List<TvShowResponse> tvShowResponses) {
        this.tvShowResponses = tvShowResponses;
    }

    @NonNull
    @Override
    public TvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_tvshow, parent, false);
        return new TvShowAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, int position) {
        TvShowResponse tvShowResponse = tvShowResponses.get(position);
        String id_ = String.valueOf(tvShowResponse.getId());
        String jenis = "TvShow";
        String judul = tvShowResponse.getJudul();
        String release = tvShowResponse.getWaktu();
        String overview = tvShowResponse.getSinopsis();
        String vote = String.valueOf(tvShowResponse.getVote());
        String backdrop = "https://image.tmdb.org/t/p/w500"+tvShowResponse.getBackdrop();
        String[] tahun = release.split("-");
        holder.tv_judul.setText(tvShowResponse.getJudul());
        holder.tv_tahun.setText(tahun[0]);
        double vote_ = Double.parseDouble(vote);
        holder.vote.setText(String.valueOf(vote_));
        String poster_path = "https://image.tmdb.org/t/p/w500"+tvShowResponse.getPoster();
        Glide.with(holder.itemView.getContext())
                .load(poster_path)
                .apply(new RequestOptions().override(350,
                        550))
                .into(holder.iv_gambar);
        holder.ly_tvshow.setOnClickListener(new View.OnClickListener() {
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
        return tvShowResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_gambar;
        TextView tv_judul, tv_tahun, vote;
        LinearLayout ly_tvshow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_tahun = itemView.findViewById(R.id.tv_tahun);
            ly_tvshow = itemView.findViewById(R.id.movie_tvshow);
            vote = itemView.findViewById(R.id.vote);
        }
    }
    public void setFilter(ArrayList<TvShowResponse> filter){
        this.tvShowResponses = filter;
        notifyDataSetChanged();
    }
}
