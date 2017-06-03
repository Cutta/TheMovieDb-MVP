package com.andcun.themoviedb_mvp.ui.movie;

import android.util.Log;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.domain.movie.MovieUseCase;
import com.andcun.themoviedb_mvp.ui.base.BasePresenter;
import com.andcun.themoviedb_mvp.util.RxTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by cuneytcarikci on 01/06/2017.
 */

public class MoviePresenter extends BasePresenter<MovieContract.View> implements MovieContract.Presenter {

    private MovieUseCase movieUseCase;
    private int pageIndex = 1;
    private int FIRST_PAGE = 1;
    private int COUNT_PER_PAGE = 20;
    private boolean isLoading;
    private MovieListType listType;

    @Inject
    public MoviePresenter(MovieUseCase movieUseCase, MovieListType listType) {
        this.movieUseCase = movieUseCase;
        this.listType = listType;
    }


    @Override
    public void onViewReady() {

        switch (MovieListType.values()[listType.ordinal()]) {
            case Popular:
                getPopularMovie(pageIndex);
                break;
            case TopRated:
                getTopRatedMovie(pageIndex);
                break;
            case NowPlaying:
                getNowPlayingMovie(pageIndex);
                break;
            case Upcoming:
                getUpcomingMovie(pageIndex);
                break;
        }
    }

    @Override
    public void decideLoadMore(int totalItemCount) {
        if (totalItemCount >= pageIndex * COUNT_PER_PAGE && !isLoading) {
            isLoading = true;
            ++pageIndex;
            switch (MovieListType.values()[listType.ordinal()]) {
                case Popular:
                    getPopularMovie(pageIndex);
                    break;
                case TopRated:
                    getTopRatedMovie(pageIndex);
                    break;
                case NowPlaying:
                    getNowPlayingMovie(pageIndex);
                    break;
                case Upcoming:
                    getUpcomingMovie(pageIndex);
                    break;
            }
        }
    }

    private void getUpcomingMovie(final int page) {
        movieUseCase.getUpcomingMovie(pageIndex).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> movies) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadMovieList(movies);
                        else {
                            isLoading = false;
                            getView().loadMoreMovieList(movies);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("ERROR", "accept: " + throwable.getMessage());
                    }
                });
    }

    private void getNowPlayingMovie(final int page) {
        movieUseCase.getNowPlayingMovie(page).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> movies) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadMovieList(movies);
                        else {
                            isLoading = false;
                            getView().loadMoreMovieList(movies);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("ERROR", "accept: " + throwable.getMessage());
                    }
                });
    }

    private void getTopRatedMovie(final int page) {
        movieUseCase.getTopRatedMovie(page).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> movies) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadMovieList(movies);
                        else {
                            isLoading = false;
                            getView().loadMoreMovieList(movies);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("ERROR", "accept: " + throwable.getMessage());
                    }
                });
    }

    private void getPopularMovie(final int page) {
        movieUseCase.getPopularMovie(page).compose(RxTransformer.<List<ResultMovie>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultMovie>>() {
                    @Override
                    public void accept(@NonNull List<ResultMovie> movies) throws Exception {
                        if (page == FIRST_PAGE)
                            getView().loadMovieList(movies);
                        else {
                            isLoading = false;
                            getView().loadMoreMovieList(movies);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("ERROR", "accept: " + throwable.getMessage());
                    }
                });
    }


}
