package com.andcun.themoviedb_mvp.ui.discover;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */

public class DiscoverContract {

    public interface View extends BaseView {

        void loadDiscoverMovie(List<ResultMovie> movieList);

        void loadMoreDiscoverMovie(List<ResultMovie> movieList);

        void loadDiscoverTv(List<ResultTv> tvList);

        void loadMoreDiscoverTv(List<ResultTv> movieList);

    }

    interface Presenter {

        void onViewReady();

        void decideLoadMore(int totalCount);//presenter karar vericek

    }
}
