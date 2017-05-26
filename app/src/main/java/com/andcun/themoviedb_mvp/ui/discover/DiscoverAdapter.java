package com.andcun.themoviedb_mvp.ui.discover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andcun.themoviedb_mvp.BuildConfig;
import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.Genre;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */

public class DiscoverAdapter<T> extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    List<T> discoverList;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    public DiscoverAdapter() {
        this.discoverList = new ArrayList<>();
    }

    public void setDiscoverList(List<T> discoverList) {
        this.discoverList = discoverList;
        notifyDataSetChanged();
    }

    public void addDiscoverList(List<T> discoverList) {
        List<T> sumList = this.discoverList;
        sumList.addAll(discoverList);
        notifyDataSetChanged();
    }


    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover, parent, false);
        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        T temp = discoverList.get(position);
        if (temp instanceof ResultMovie) {
            ResultMovie tempMovie = (ResultMovie) temp;
            Glide.with(holder.itemView.getContext()).
                    load(BuildConfig.IMAGE_PREFIX + tempMovie.getBackdropPath())
                    .into(holder.ivPoster);
            holder.tvTitle.setText(tempMovie.getTitle());
            holder.tvGenres.setText(Genre.getGenresText(tempMovie.getGenreIds()));
            holder.tvVoteAverage.setText(String.valueOf(tempMovie.getVoteAverage()));
            holder.tvReleaseYear.setText(dateFormat.format(tempMovie.getReleaseDate()));
        } else {
            ResultTv tempTv = (ResultTv) temp;
            Glide.with(holder.itemView.getContext()).
                    load(BuildConfig.IMAGE_PREFIX + tempTv.getBackdropPath())
                    .into(holder.ivPoster);
            holder.tvTitle.setText(tempTv.getName());
            holder.tvGenres.setText(Genre.getGenresText(tempTv.getGenreIds()));
            holder.tvVoteAverage.setText(String.valueOf(tempTv.getVoteAverage()));
            holder.tvReleaseYear.setText(dateFormat.format(tempTv.getFirstAirDate()));
        }

    }

    @Override
    public int getItemCount() {
        return discoverList.size();
    }

    static class DiscoverViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_poster)
        ImageView ivPoster;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_genres)
        TextView tvGenres;
        @BindView(R.id.tv_vote_average)
        TextView tvVoteAverage;
        @BindView(R.id.tv_release_year)
        TextView tvReleaseYear;

        public DiscoverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
