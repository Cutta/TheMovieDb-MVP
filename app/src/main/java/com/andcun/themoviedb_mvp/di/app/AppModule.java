package com.andcun.themoviedb_mvp.di.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.andcun.themoviedb_mvp.data.pref.PreferencesHelper;
import com.andcun.themoviedb_mvp.data.pref.PreferencesHelperImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

@Module
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences("the_movie_db_prefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(SharedPreferences preferences, Gson gson) {
        return new PreferencesHelperImpl(preferences, gson);
    }
}
