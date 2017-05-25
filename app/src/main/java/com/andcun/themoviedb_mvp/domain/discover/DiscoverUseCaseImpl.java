package com.andcun.themoviedb_mvp.domain.discover;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by cuneytcarikci on 24/05/2017.
 */

public class DiscoverUseCaseImpl implements DiscoverUseCase {

    ApiSource apiSource;

    public DiscoverUseCaseImpl(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    @Override
    public Observable<List<ResultMovie>> getDiscoverMovie(int page) {
        return apiSource.getDiscoverMovie(page).map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
            @Override
            public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }

    @Override
    public Observable<List<ResultTv>> getDiscoverTv(int page) {
        return apiSource.getDiscoverTv(page).map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
            @Override
            public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> resultList) throws Exception {
                return resultList.getResults();
            }
        });
    }
}
