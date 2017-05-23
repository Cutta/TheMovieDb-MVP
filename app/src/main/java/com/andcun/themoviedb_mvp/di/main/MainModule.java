package com.andcun.themoviedb_mvp.di.main;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.domain.main.MainUseCase;
import com.andcun.themoviedb_mvp.domain.main.MainUseCaseImpl;
import com.andcun.themoviedb_mvp.ui.main.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    MainContract.View provideView() {
        return view;
    }

    @PerActivity
    @Provides
    MainUseCase provideMainUsecase(ApiSource apiSource) {
        return new MainUseCaseImpl(apiSource);
    }
}
