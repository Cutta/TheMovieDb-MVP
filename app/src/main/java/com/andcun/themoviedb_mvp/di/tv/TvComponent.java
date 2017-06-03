package com.andcun.themoviedb_mvp.di.tv;

import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.di.app.AppComponent;
import com.andcun.themoviedb_mvp.ui.tv.TvActivity;

import dagger.Component;

/**
 * Created by andani on 2.06.2017.
 */
@PerActivity
@Component(modules = TvModule.class, dependencies = AppComponent.class)
public interface TvComponent {

    void inject(TvActivity tvActivity);

}