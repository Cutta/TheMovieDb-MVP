package com.andcun.themoviedb_mvp.di.movie;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.domain.movie.MovieUseCase;
import com.andcun.themoviedb_mvp.domain.movie.MovieUseCaseImpl;
import com.andcun.themoviedb_mvp.ui.movie.MovieContract;
import com.andcun.themoviedb_mvp.ui.movie.MovieListType;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuneytcarikci on 02/06/2017.
 */

@Module
public class MovieModule {

    MovieContract.View movieView;
    MovieListType listType;

    public MovieModule(MovieContract.View movieView, MovieListType listType) {
        this.movieView = movieView;
        this.listType = listType;
    }

    @PerActivity
    @Provides
    MovieUseCase provideMovieUseCase(ApiSource apiSource) {
        return new MovieUseCaseImpl(apiSource);
    }

    @PerActivity
    @Provides
    MovieContract.View provideMovieView() {
        return movieView;
    }

    @PerActivity
    @Provides
    MovieListType provideListType() {
        return listType;
    }
}
