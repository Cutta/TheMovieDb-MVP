package com.andcun.themoviedb_mvp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andcun.themoviedb_mvp.BuildConfig;
import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class MainAdapter<T> extends RecyclerView.Adapter<MainAdapter.MainMovieViewHolder> {

    List<T> movieList;

    public MainAdapter() {
        this.movieList = new ArrayList<>();
    }

    public void setMovieList(List<T> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MainMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_tv_or_movie, parent, false);

        return new MainMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainMovieViewHolder holder, int position) {

        T temp = movieList.get(position);
        if (temp instanceof ResultMovie) {
            ResultMovie tempResultMovie = (ResultMovie)movieList.get(position);

            holder.tvTitle.setText(tempResultMovie.getTitle());

            Glide.with(holder.itemView.getContext())
                    .load(BuildConfig.IMAGE_PREFIX + tempResultMovie.getBackdropPath())
                    .into(holder.ivPoster);
        }else {
            ResultTv tempTv = (ResultTv) temp;

            holder.tvTitle.setText(tempTv.getName());

            Glide.with(holder.itemView.getContext())
                    .load(BuildConfig.IMAGE_PREFIX + tempTv.getPosterPath())
                    .into(holder.ivPoster);

        }


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MainMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView ivPoster;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public MainMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

