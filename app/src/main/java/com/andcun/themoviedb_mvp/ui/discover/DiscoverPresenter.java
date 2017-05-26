package com.andcun.themoviedb_mvp.ui.discover;

import android.util.Log;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.domain.discover.DiscoverUseCase;
import com.andcun.themoviedb_mvp.ui.base.BasePresenter;
import com.andcun.themoviedb_mvp.util.RxTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */

public class DiscoverPresenter extends BasePresenter<DiscoverContract.View> implements DiscoverContract.Presenter {

    private DiscoverUseCase discoverUseCase;

    private int pageIndex = 1;
    private int FIRST_PAGE = 1;
    private int COUNT_PER_PAGE = 20;

    @Inject
    public DiscoverPresenter(DiscoverUseCase discoverUseCase) {
        this.discoverUseCase = discoverUseCase;
    }

    @Override
    public void onViewReady() {
        getDiscoverMovie(pageIndex);
        // getDiscoverTv(); // TODO: 25/05/2017 şimdilik sadece filmler gelsin diye
    }


    @Override
    public void decideLoadMore(int totalItemCount) {
        if (totalItemCount >= pageIndex * COUNT_PER_PAGE) {
            getView().setLoadFlag(true);//artarda istek yapmasin diye
            ++pageIndex;
            getDiscoverMovie(pageIndex);
        }
    }

    private void getDiscoverMovie(final int page) {
        discoverUseCase.getDiscoverMovie(page).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> resultMovies) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadDiscoverMovie(resultMovies);
                        else {
                            getView().setLoadFlag(false);
                            getView().loadMoreDiscoverMovie(resultMovies);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 25/05/2017
                    }
                });

    }

    private void getDiscoverTv() {
        discoverUseCase.getDiscoverTv(1).compose(RxTransformer.<List<ResultTv>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultTv>>() {
                    @Override
                    public void accept(@NonNull List<ResultTv> tvList) throws Exception {
                        getView().loadDiscoverTv(tvList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 25/05/2017
                    }
                });
    }
}
