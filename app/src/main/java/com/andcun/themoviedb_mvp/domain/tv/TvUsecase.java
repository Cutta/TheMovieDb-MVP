package com.andcun.themoviedb_mvp.domain.tv;

import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by andani on 2.06.2017.
 */

public interface TvUsecase {

    Observable<List<ResultTv>> getPopularTv(int page);

    Observable<List<ResultTv>> getTopRatedTv(int page);

    Observable<List<ResultTv>> getOnTheAirTv(int page);

    Observable<List<ResultTv>> getAiringTodayTv(int page);

}