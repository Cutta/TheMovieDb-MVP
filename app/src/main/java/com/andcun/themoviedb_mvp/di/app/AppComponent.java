package com.andcun.themoviedb_mvp.di.app;

import android.content.Context;

import com.andcun.themoviedb_mvp.TmdbApp;
import com.andcun.themoviedb_mvp.data.pref.PreferencesHelper;
import com.andcun.themoviedb_mvp.data.rest.ApiSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    Context context();

    ApiSource apiSource();

    PreferencesHelper preferencesHelper();

    void inject(TmdbApp app);

}
