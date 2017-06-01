package com.andcun.themoviedb_mvp.data.rest;

import com.andcun.themoviedb_mvp.BuildConfig;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseRequestToken;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseSessionId;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.Locale;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class ApiSourceImpl implements ApiSource {

    private RetrofitInterface retrofitInterface;


    public ApiSourceImpl(Retrofit retrofit) {
        this.retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public Observable<ResponseRequestToken> getRequestToken(String apiKey) {
        return retrofitInterface.getRequestToken(apiKey);
    }

    @Override
    public Observable<ResponseSessionId> getSessionId(String apiKey, String requestToken) {
        return retrofitInterface.getSessionId(apiKey, requestToken);
    }

    @Override
    public Observable<ResponseResultList<ResultTv>> getTvOnTheAir(int page) {
        return retrofitInterface.getTvOnTheAir(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultTv>> getPopularTv(String apiKey, String language, int page) {
        return retrofitInterface.getPopularTv(apiKey, language, page);
    }

    @Override
    public Observable<ResponseResultList<ResultTv>> getTopRated(String apiKey, String language, int page) {
        return retrofitInterface.getTopRated(apiKey, language, page);
    }

    @Override
    public Observable<ResponseResultList<ResultTv>> getAiringToday(String apiKey, String language, int page) {
        return retrofitInterface.getAiringToday(apiKey, language, page);
    }

    @Override
    public Observable<ResponseResultList<ResultMovie>> getPopularMovie(int page) {
        return retrofitInterface.getPopularMovie(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultMovie>> getTopRatedMovie(int page) {
        return retrofitInterface.getTopRatedMovie(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultMovie>> getUpcomingMovie(int page) {
        return retrofitInterface.getUpcomingMovie(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultMovie>> getNowPlayingMovie(int page) {
        return retrofitInterface.getNowPlayingMovie(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultMovie>> getDiscoverMovie(int page) {
        return retrofitInterface.getDiscoverMovie(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Observable<ResponseResultList<ResultTv>> getDiscoverTv(int page) {
        return retrofitInterface.getDiscoverTv(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }
}
