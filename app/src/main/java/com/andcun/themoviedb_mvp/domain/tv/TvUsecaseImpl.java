package com.andcun.themoviedb_mvp.domain.tv;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by andani on 2.06.2017.
 */

public class TvUsecaseImpl implements TvUsecase {

    private ApiSource mApiSource;

    public TvUsecaseImpl(ApiSource apiSource) {
        this.mApiSource = apiSource;
    }

    @Override
    public Observable<List<ResultTv>> getPopularTv(int page) {
        return mApiSource.getPopularTv(page)
                .map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
                    @Override
                    public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> result) throws Exception {
                        return result.getResults();
                    }
                });
    }

    @Override
    public Observable<List<ResultTv>> getTopRatedTv(int page) {
        return mApiSource.getTopRatedTv(page)
                .map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
                    @Override
                    public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> result) throws Exception {
                        return result.getResults();
                    }
                });
    }

    @Override
    public Observable<List<ResultTv>> getOnTheAirTv(int page) {
        return mApiSource.getTvOnTheAir(page)
                .map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
                    @Override
                    public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> result) throws Exception {
                        return result.getResults();
                    }
                });
    }

    @Override
    public Observable<List<ResultTv>> getAiringTodayTv(int page) {
        return mApiSource.getAiringTodayTv(page)
                .map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
                    @Override
                    public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> result) throws Exception {
                        return result.getResults();
                    }
                });
    }

}