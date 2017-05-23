package com.andcun.themoviedb_mvp.domain.main;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class MainUseCaseImpl implements MainUseCase {

    private ApiSource apiSource;

    private static final int FIRST_PAGE = 1;

    private static final int MAX_ITEM_COUNT = 5;

    public MainUseCaseImpl(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    @Override
    public Observable<List<ResultTv>> getTvOnTheAir() {
        return apiSource.getTvOnTheAir(FIRST_PAGE)
                .map(new Function<ResponseResultList<ResultTv>, List<ResultTv>>() {
                    @Override
                    public List<ResultTv> apply(@NonNull ResponseResultList<ResultTv> resultList) throws Exception {
                        if (resultList.getResults().size() > MAX_ITEM_COUNT)
                            return resultList.getResults().subList(0, MAX_ITEM_COUNT);
                        else
                            return resultList.getResults();
                    }
                });
    }

    @Override
    public Observable<List<ResultMovie>> getMovieNowPlaying() {
        return apiSource.getNowPlayingMovie(FIRST_PAGE)
                .map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
                    @Override
                    public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                        if (resultList.getResults().size() > MAX_ITEM_COUNT)
                            return resultList.getResults().subList(0, MAX_ITEM_COUNT);
                        else
                            return resultList.getResults();
                    }
                });
    }

    @Override
    public Observable<List<ResultMovie>> getMovieUpComing() {
        return apiSource.getUpcomingMovie(FIRST_PAGE)
                .map(new Function<ResponseResultList<ResultMovie>, List<ResultMovie>>() {
                    @Override
                    public List<ResultMovie> apply(@NonNull ResponseResultList<ResultMovie> resultList) throws Exception {
                        if (resultList.getResults().size() > MAX_ITEM_COUNT)
                            return resultList.getResults().subList(0, MAX_ITEM_COUNT);
                        else
                            return resultList.getResults();
                    }
                });
    }
}
