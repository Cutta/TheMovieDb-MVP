package com.andcun.themoviedb_mvp.ui.tv;

import android.support.annotation.StringRes;

import com.andcun.themoviedb_mvp.R;

/**
 * Created by andani on 3.06.2017.
 */

public enum TvType {

    Popular,
    TopRated,
    OnTheAir,
    AiringToday;

    @StringRes
    public Integer getTitle() {
        switch (this) {
            case Popular:
                return R.string.title_tv_popular;
            case TopRated:
                return R.string.title_tv_top_rated;
            case OnTheAir:
                return R.string.title_tv_on_the_air;
            case AiringToday:
                return R.string.title_tv_airing_today;
            default:
                return R.string.title_tv_default;
        }
    }

}