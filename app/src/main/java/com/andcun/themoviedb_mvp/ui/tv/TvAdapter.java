package com.andcun.themoviedb_mvp.ui.tv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andcun.themoviedb_mvp.BuildConfig;
import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.Genre;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andani on 2.06.2017.
 */

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private List<ResultTv> mTvList;
    private SimpleDateFormat mDateFormat;

    public TvAdapter(List<ResultTv> tvList) {
        this.mTvList = tvList;
        this.mDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    }

    public void loadMoreTv(List<ResultTv> newTvList) {
        int listOldSize = mTvList.size();
        mTvList.addAll(newTvList);
        notifyItemRangeInserted(listOldSize, mTvList.size() - listOldSize);
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false);
        return new TvViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        ResultTv tv = mTvList.get(holder.getAdapterPosition());
        Glide.with(holder.itemView.getContext()).
                load(BuildConfig.IMAGE_PREFIX + tv.getBackdropPath())
                .into(holder.ivPoster);
        holder.tvTitle.setText(tv.getName());
        holder.tvGenres.setText(Genre.getGenresText(tv.getGenreIds()));
        holder.tvVoteAverage.setText(String.valueOf(tv.getVoteAverage()));
        holder.tvReleaseYear.setText(mDateFormat.format(tv.getFirstAirDate()));
    }

    @Override
    public int getItemCount() {
        return mTvList.size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder {

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

        TvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}