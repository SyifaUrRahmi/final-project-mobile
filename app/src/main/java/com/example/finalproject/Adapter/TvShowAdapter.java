package com.example.finalproject.Adapter;

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
import com.example.finalproject.Data.MovieResponse;
import com.example.finalproject.Data.TvShowResponse;
import com.example.finalproject.R;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private final List<TvShowResponse> tvShowResponses;

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
        String id = String.valueOf(tvShowResponse.getId());
        String tahun = tvShowResponse.getWaktu();
        String[] separated = tahun.split("-");
        holder.tv_judul.setText(tvShowResponse.getJudul());
        holder.tv_tahun.setText(separated[0]);
        String poster_path = "https://image.tmdb.org/t/p/w500"+tvShowResponse.getPoster();
        Glide.with(holder.itemView.getContext())
                .load(poster_path)
                .apply(new RequestOptions().override(350,
                        550))
                .into(holder.iv_gambar);
    }

    @Override
    public int getItemCount() {
        return tvShowResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_gambar;
        TextView tv_judul, tv_tahun;
        LinearLayout ly_tvshow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_tahun = itemView.findViewById(R.id.tv_tahun);
            ly_tvshow = itemView.findViewById(R.id.movie_tvshow);
        }
    }
}
