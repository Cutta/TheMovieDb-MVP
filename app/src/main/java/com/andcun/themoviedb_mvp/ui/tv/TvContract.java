package com.andcun.themoviedb_mvp.ui.tv;

import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by andani on 2.06.2017.
 */

public class TvContract {

    public interface View extends BaseView {

        void loadMoreTv(List<ResultTv> tvList);

        void showError(String message);

    }

    interface Presenter {

        void onViewReady();

        void onScrollToEndOfList();

    }

}