package com.andcun.themoviedb_mvp.di.discover;

import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.di.app.AppComponent;
import com.andcun.themoviedb_mvp.ui.discover.DiscoverActivity;

import dagger.Component;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */
@PerActivity
@Component(modules = DiscoverModule.class, dependencies = AppComponent.class)
public interface DiscoverComponent {

    void inject(DiscoverActivity activity);

}
