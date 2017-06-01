package com.andcun.themoviedb_mvp.domain.movie;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cuneytcarikci on 01/06/2017.
 */

public interface MovieUseCase {

    Observable<List<ResultMovie>> getPopularMovie(int page);

    Observable<List<ResultMovie>> getTopRatedMovie(int page);

    Observable<List<ResultMovie>> getUpcomingMovie(int page);

    Observable<List<ResultMovie>> getNowPlayingMovie(int page);
}
