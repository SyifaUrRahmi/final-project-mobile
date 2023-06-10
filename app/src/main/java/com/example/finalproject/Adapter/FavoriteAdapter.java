package com.example.finalproject.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalproject.Data_API.MovieResponse;
import com.example.finalproject.Database.Detail;
import com.example.finalproject.DetailActivity;
import com.example.finalproject.R;

import org.w3c.dom.Text;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<Detail> details;

    public FavoriteAdapter(List<Detail> details) {
        this.details = details;
    }


    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Detail detail = details.get(position);
        String jenis = detail.getJenis();
        String judul = detail.getJudul();
        String release = detail.getWaktu();
        String overview = detail.getSinopsis();
        String vote = String.valueOf(detail.getVote());
        String backdrop = detail.getBackdrop();
        String[] tahun = release.split("-");
        holder.jud.setText(detail.getJudul());
        holder.release.setText(tahun[0]);
        String poster = detail.getPoster();
        Glide.with(holder.itemView.getContext())
                .load(poster)
                .apply(new RequestOptions().override(350,
                        550))
                .into(holder.post);
        holder.rating.setText(vote);
        if(jenis.equals("movie")){
            holder.jenis.setImageResource(R.drawable.baseline_movie_creation_24);
        } else {
            holder.jenis.setImageResource(R.drawable.baseline_tv_24);
        }
        holder.cv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
                toDetail.putExtra(DetailActivity.EXTRA_JENIS, jenis);
                toDetail.putExtra(DetailActivity.EXTRA_JUDUL, judul);
                toDetail.putExtra(DetailActivity.EXTRA_BACK, backdrop);
                toDetail.putExtra(DetailActivity.EXTRA_OVERVIEW, overview);
                toDetail.putExtra(DetailActivity.EXTRA_RELEASE, release);
                toDetail.putExtra(DetailActivity.EXTRA_POSTER, poster);
                toDetail.putExtra(DetailActivity.EXTRA_VOTE, vote);
                holder.itemView.getContext().startActivity(toDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView post, jenis;
        TextView jud, release, rating;
        CardView cv_fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.post);
            jenis = itemView.findViewById(R.id.jenis);
            jud = itemView.findViewById(R.id.jud);
            release = itemView.findViewById(R.id.release);
            cv_fav = itemView.findViewById(R.id.cv_fav);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
