package com.andcun.themoviedb_mvp.ui.tv;

import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.domain.tv.TvUsecase;
import com.andcun.themoviedb_mvp.ui.base.BasePresenter;
import com.andcun.themoviedb_mvp.util.RxTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by andani on 2.06.2017.
 */

public class TvPresenter extends BasePresenter<TvContract.View> implements TvContract.Presenter {

    private TvUsecase mTvUsecase;

    private int mCurrentPage;
    private boolean mEndOfList;
    private boolean mRequestExist;
    private TvType mTvType;

    private final int mFirstPage = 1;
    private final int mTvCountPerPage = 20;

    @Inject
    public TvPresenter(TvUsecase tvUsecase, TvType tvType) {
        this.mTvUsecase = tvUsecase;
        this.mTvType = tvType;
    }

    @Override
    public void onViewReady() {
        mCurrentPage = 1;
        loadTv();
    }

    @Override
    public void onScrollToEndOfList() {
        if (mRequestExist || mEndOfList)
            return;

        mCurrentPage++;
        loadTv();
    }

    private void loadTv() {
        mRequestExist = true;

        Observable<List<ResultTv>> observable = null;

        switch (mTvType) {
            case Popular:
                observable = mTvUsecase.getPopularTv(mCurrentPage);
                break;
            case TopRated:
                observable = mTvUsecase.getTopRatedTv(mCurrentPage);
                break;
            case OnTheAir:
                observable = mTvUsecase.getOnTheAirTv(mCurrentPage);
                break;
            case AiringToday:
                observable = mTvUsecase.getAiringTodayTv(mCurrentPage);
                break;
        }

        observable
                .compose(RxTransformer.<List<ResultTv>>applyIOSchedulers())
                .subscribe(new Consumer<List<ResultTv>>() {
                    @Override
                    public void accept(@NonNull List<ResultTv> tvList) throws Exception {
                        getView().loadMoreTv(tvList);
                        mRequestExist = false;
                        mEndOfList = tvList.size() < mTvCountPerPage;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        getView().showError(throwable.getMessage());
                        mRequestExist = false;

                        if (mCurrentPage > mFirstPage)
                            mCurrentPage--;
                    }
                });
    }

}