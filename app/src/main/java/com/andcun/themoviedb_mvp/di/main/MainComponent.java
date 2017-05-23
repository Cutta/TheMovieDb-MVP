package com.andcun.themoviedb_mvp.di.main;

import com.andcun.themoviedb_mvp.ui.main.MainActivity;
import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.di.app.AppComponent;

import dagger.Component;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
