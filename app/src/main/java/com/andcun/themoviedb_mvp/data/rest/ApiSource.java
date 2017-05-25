package com.andcun.themoviedb_mvp.data.rest;

import com.andcun.themoviedb_mvp.data.rest.model.ResponseRequestToken;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseSessionId;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import io.reactivex.Observable;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public interface ApiSource {

    Observable<ResponseRequestToken> getRequestToken(String apiKey);

    Observable<ResponseSessionId> getSessionId(String apiKey, String requestToken);

    Observable<ResponseResultList<ResultTv>> getTvOnTheAir(int page);

    Observable<ResponseResultList<ResultTv>> getPopularTv(String apiKey, String language, int page);

    Observable<ResponseResultList<ResultTv>> getTopRated(String apiKey, String language, int page);

    Observable<ResponseResultList<ResultTv>> getAiringToday(String apiKey, String language, int page);

    Observable<ResponseResultList<ResultMovie>> getPopularMovie(String apiKey, String language, int page);

    Observable<ResponseResultList<ResultMovie>> getTopRatedMovie(String apiKey, String language, int page);

    Observable<ResponseResultList<ResultMovie>> getUpcomingMovie(int page);

    Observable<ResponseResultList<ResultMovie>> getNowPlayingMovie(int page);

    Observable<ResponseResultList<ResultMovie>> getDiscoverMovie(int page);

    Observable<ResponseResultList<ResultTv>> getDiscoverTv(int page);
}
