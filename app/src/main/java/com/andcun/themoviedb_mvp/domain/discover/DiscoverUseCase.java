package com.andcun.themoviedb_mvp.domain.discover;

import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cuneytcarikci on 24/05/2017.
 */

public interface DiscoverUseCase {

    Observable<List<ResultMovie>> getDiscoverMovie(int page);

    Observable<List<ResultTv>> getDiscoverTv(int page);

}
