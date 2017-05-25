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

    @Inject
    public DiscoverPresenter(DiscoverUseCase discoverUseCase) {
        this.discoverUseCase = discoverUseCase;
    }

    @Override
    public void onViewReady() {
        getDiscoverMovie();
        // getDiscoverTv(); // TODO: 25/05/2017 şimdilik sadece filmler gelsin diye
    }

    private void getDiscoverMovie() {//// TODO: 25/05/2017 1 olmayacak burasi degisken olacak
        discoverUseCase.getDiscoverMovie(1).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> resultMovies) throws Exception {
                        getView().loadDiscoverMovie(resultMovies);
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
