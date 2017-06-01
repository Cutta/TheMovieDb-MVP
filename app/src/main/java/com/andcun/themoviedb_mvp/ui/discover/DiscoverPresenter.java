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
    private boolean isLoading;

    @Inject
    public DiscoverPresenter(DiscoverUseCase discoverUseCase) {
        this.discoverUseCase = discoverUseCase;
    }

    @Override
    public void onViewReady() {
        //if (condition)
        getDiscoverMovie(pageIndex);
        // getDiscoverTv(pageIndex); // TODO: 25/05/2017 şimdilik sadece filmler gelsin diye
    }


    @Override
    public void decideLoadMore(int totalItemCount) {
        if (totalItemCount >= pageIndex * COUNT_PER_PAGE && !isLoading) {
            isLoading = true;
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
                            isLoading = false;
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

    private void getDiscoverTv(final int page) {
        discoverUseCase.getDiscoverTv(1).compose(RxTransformer.<List<ResultTv>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultTv>>() {
                    @Override
                    public void accept(@NonNull List<ResultTv> tvList) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadDiscoverTv(tvList);
                        else {
                            isLoading = false;
                            getView().loadMoreDiscoverTv(tvList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 25/05/2017
                    }
                });
    }
}
