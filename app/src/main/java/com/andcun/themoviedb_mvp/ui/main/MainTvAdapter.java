package com.andcun.themoviedb_mvp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andcun.themoviedb_mvp.BuildConfig;
import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class MainTvAdapter extends RecyclerView.Adapter<MainTvAdapter.MainTvViewHolder> {

    List<ResultTv> tvList;

    public MainTvAdapter() {
        this.tvList = new ArrayList<>();
    }

    public void setTvList(List<ResultTv> tvList) {
        this.tvList = tvList;
        notifyDataSetChanged();
    }

    @Override
    public MainTvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_tv_or_movie, parent, false);

        return new MainTvViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainTvViewHolder holder, int position) {
        ResultTv tempResultTv = tvList.get(position);

        holder.tvTitle.setText(tempResultTv.getName());

        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.IMAGE_PREFIX + tempResultTv.getPosterPath())
                .into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    static class MainTvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView ivPoster;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public MainTvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
