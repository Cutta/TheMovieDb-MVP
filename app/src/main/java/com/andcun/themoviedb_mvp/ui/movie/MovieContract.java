package com.andcun.themoviedb_mvp.ui.movie;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by cuneytcarikci on 01/06/2017.
 */

public class MovieContract {

    public interface View extends BaseView {

        void loadMovieList(List<ResultMovie> movieList);

        void loadMoreMovieList(List<ResultMovie> movieList);

    }

    interface Presenter {

        void onViewReady();

        void decideLoadMore(int totalCount);

    }
}
