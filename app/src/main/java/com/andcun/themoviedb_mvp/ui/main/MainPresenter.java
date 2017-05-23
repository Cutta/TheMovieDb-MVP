package com.andcun.themoviedb_mvp.ui.main;

import android.util.Log;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.domain.main.MainUseCase;
import com.andcun.themoviedb_mvp.ui.base.BasePresenter;
import com.andcun.themoviedb_mvp.util.RxTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainUseCase mainUseCase;

    @Inject
    public MainPresenter(MainUseCase mainUseCase) {
        this.mainUseCase = mainUseCase;
    }

    @Override
    public void onViewReady() {

        getTvOnTheAir();
        getMovieNowPlaying();
        getMovieUpComing();

    }

    private void getTvOnTheAir() {
        mainUseCase.getTvOnTheAir()
                .compose(RxTransformer.<List<ResultTv>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultTv>>() {
                    @Override
                    public void accept(@NonNull List<ResultTv> tvList) throws Exception {
                        getView().loadOnTheAirTvList(tvList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 24/05/2017
                    }
                });
    }

    private void getMovieNowPlaying() {
        mainUseCase.getMovieNowPlaying().compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> resultMovies) throws Exception {
                        getView().loadNowPlayingMovieList(resultMovies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 24/05/2017
                    }
                });

    }

    private void getMovieUpComing() {

        mainUseCase.getMovieUpComing().compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> resultMovies) throws Exception {
                        getView().loadUpcomingMovieList(resultMovies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("accept", "accept: " + throwable.getMessage());//// TODO: 24/05/2017
                    }
                });

    }
}
