package com.andcun.themoviedb_mvp.data.rest;

import com.andcun.themoviedb_mvp.data.rest.model.ResponseRequestToken;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseResultList;
import com.andcun.themoviedb_mvp.data.rest.model.ResponseSessionId;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

interface RetrofitInterface {

    //https://api.themoviedb.org/3

    @GET("authentication/token/new")
    Observable<ResponseRequestToken> getRequestToken(@Query("api_key") String apiKey);

    @GET("authentication/session/new")
    Observable<ResponseSessionId> getSessionId(@Query("api_key") String apiKey,
                                               @Query("request_token") String requestToken);

    //TV
    @GET("tv/on_the_air")
    Observable<ResponseResultList<ResultTv>> getTvOnTheAir(@Query("api_key") String apiKey,
                                                           @Query("language") String language,
                                                           @Query("page") int page);

    @GET("tv/popular")
    Observable<ResponseResultList<ResultTv>> getPopularTv(@Query("api_key") String apiKey,
                                                          @Query("language") String language,
                                                          @Query("page") int page);

    @GET("tv/top_rated")
    Observable<ResponseResultList<ResultTv>> getTopRated(@Query("api_key") String apiKey,
                                                         @Query("language") String language,
                                                         @Query("page") int page);

    @GET("tv/airing_today")
    Observable<ResponseResultList<ResultTv>> getAiringToday(@Query("api_key") String apiKey,
                                                            @Query("language") String language,
                                                            @Query("page") int page);

    //FILM

    @GET("movie/popular")
    Observable<ResponseResultList<ResultMovie>> getPopularMovie(@Query("api_key") String apiKey,
                                                                @Query("language") String language,
                                                                @Query("page") int page);

    @GET("movie/top_rated")
    Observable<ResponseResultList<ResultMovie>> getTopRatedMovie(@Query("api_key") String apiKey,
                                                                 @Query("language") String language,
                                                                 @Query("page") int page);

    @GET("movie/upcoming")
    Observable<ResponseResultList<ResultMovie>> getUpcomingMovie(@Query("api_key") String apiKey,
                                                                 @Query("language") String language,
                                                                 @Query("page") int page);

    @GET("movie/now_playing")
    Observable<ResponseResultList<ResultMovie>> getNowPlayingMovie(@Query("api_key") String apiKey,
                                                                   @Query("language") String language,
                                                                   @Query("page") int page);


}
