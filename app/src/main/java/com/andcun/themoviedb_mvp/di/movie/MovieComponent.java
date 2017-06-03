package com.andcun.themoviedb_mvp.di.movie;

import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.di.app.AppComponent;
import com.andcun.themoviedb_mvp.ui.movie.MovieActivity;

import dagger.Component;

/**
 * Created by cuneytcarikci on 02/06/2017.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = MovieModule.class)
public interface MovieComponent {

    void inject(MovieActivity movieActivity);

}
