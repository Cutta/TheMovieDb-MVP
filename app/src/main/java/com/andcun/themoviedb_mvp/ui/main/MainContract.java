package com.andcun.themoviedb_mvp.ui.main;

import android.support.annotation.StringRes;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class MainContract {

    public interface View extends BaseView {

        boolean isConnect();

        void showSnackBar(@StringRes int message);

        void loadOnTheAirTvList(List<ResultTv> tvList);

        void loadNowPlayingMovieList(List<ResultMovie> movieList);

        void loadUpcomingMovieList(List<ResultMovie> movieList);

    }

    interface Presenter {

        void onViewReady();

    }
}
