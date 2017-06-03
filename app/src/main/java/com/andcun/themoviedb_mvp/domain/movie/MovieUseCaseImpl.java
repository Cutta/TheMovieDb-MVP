package com.andcun.themoviedb_mvp.domain.movie;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by cuneytcarikci on 01/06/2017.
 */

public class MovieUseCaseImpl implements MovieUseCase {

    private ApiSource apiSource;

    public MovieUseCaseImpl(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    @Override
    public Observable<List<ResultMovie>> getPopularMovie(int page) {
        return apiSource.getPopularMovie(page).map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
            @Override
            public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }

    @Override
    public Observable<List<ResultMovie>> getTopRatedMovie(int page) {
        return apiSource.getTopRatedMovie(page).map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
            @Override
            public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }

    @Override
    public Observable<List<ResultMovie>> getUpcomingMovie(int page) {
        return apiSource.getUpcomingMovie(page).map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
            @Override
            public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }

    @Override
    public Observable<List<ResultMovie>> getNowPlayingMovie(int page) {
        return apiSource.getNowPlayingMovie(page).map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
            @Override
            public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }
}
