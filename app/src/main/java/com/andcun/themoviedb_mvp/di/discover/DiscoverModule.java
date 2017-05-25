package com.andcun.themoviedb_mvp.di.discover;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.domain.discover.DiscoverUseCase;
import com.andcun.themoviedb_mvp.domain.discover.DiscoverUseCaseImpl;
import com.andcun.themoviedb_mvp.ui.discover.DiscoverContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */

@Module
public class DiscoverModule {

    DiscoverContract.View discoverView;

    public DiscoverModule(DiscoverContract.View discoverView) {
        this.discoverView = discoverView;
    }

    @PerActivity
    @Provides
    DiscoverUseCase provideDiscoverUseCase(ApiSource apiSource) {
        return new DiscoverUseCaseImpl(apiSource);
    }

    @PerActivity
    @Provides
    DiscoverContract.View provideDiscoverView() {
        return discoverView;
    }

}
