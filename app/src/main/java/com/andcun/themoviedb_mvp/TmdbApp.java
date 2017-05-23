package com.andcun.themoviedb_mvp;

import android.app.Application;

import com.andcun.themoviedb_mvp.di.app.AppComponent;
import com.andcun.themoviedb_mvp.di.app.AppModule;
import com.andcun.themoviedb_mvp.di.app.DaggerAppComponent;
import com.andcun.themoviedb_mvp.di.app.NetworkModule;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class TmdbApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule()).build();

        appComponent.inject(this);

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
