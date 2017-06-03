package com.andcun.themoviedb_mvp.di.tv;

import com.andcun.themoviedb_mvp.data.rest.ApiSource;
import com.andcun.themoviedb_mvp.di.PerActivity;
import com.andcun.themoviedb_mvp.domain.tv.TvUsecase;
import com.andcun.themoviedb_mvp.domain.tv.TvUsecaseImpl;
import com.andcun.themoviedb_mvp.ui.tv.TvContract;
import com.andcun.themoviedb_mvp.ui.tv.TvType;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andani on 2.06.2017.
 */
@Module
public class TvModule {

    private TvContract.View mView;
    private TvType mTvType;

    public TvModule(TvContract.View view, TvType tvType) {
        this.mView = view;
        this.mTvType = tvType;
    }

    @PerActivity
    @Provides
    TvUsecase provideTvUsecase(ApiSource apiSource) {
        return new TvUsecaseImpl(apiSource);
    }

    @PerActivity
    @Provides
    TvType provideTvType() {
        return mTvType;
    }

    @PerActivity
    @Provides
    TvContract.View provideView() {
        return mView;
    }

}