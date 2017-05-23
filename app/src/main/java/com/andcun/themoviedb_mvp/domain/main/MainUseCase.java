package com.andcun.themoviedb_mvp.domain.main;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public interface MainUseCase {

    Observable<List<ResultTv>> getTvOnTheAir();

    Observable<List<ResultMovie>> getMovieNowPlaying();

    Observable<List<ResultMovie>> getMovieUpComing();

}
