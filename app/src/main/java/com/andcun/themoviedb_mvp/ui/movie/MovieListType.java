package com.andcun.themoviedb_mvp.ui.movie;

import android.support.annotation.StringRes;

import com.andcun.themoviedb_mvp.R;

/**
 * Created by cuneytcarikci on 02/06/2017.
 */

public enum MovieListType {

    Popular,
    TopRated,
    NowPlaying,
    Upcoming;

    @StringRes
    public int getTitle() {
        switch (this) {
            case Popular:
                return R.string.movie_list_title_popular;
            case TopRated:
                return R.string.movie_list_title_top_rated;
            case NowPlaying:
                return R.string.movie_list_title_now_playing;
            case Upcoming:
                return R.string.movie_list_title_upcoming;
            default:
                return R.string.movie_list_title_default;
        }
    }


}
