package com.andcun.themoviedb_mvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.di.main.DaggerMainComponent;
import com.andcun.themoviedb_mvp.di.main.MainModule;
import com.andcun.themoviedb_mvp.ui.base.BaseActivity;
import com.andcun.themoviedb_mvp.ui.discover.DiscoverActivity;
import com.andcun.themoviedb_mvp.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.root_layout)
    LinearLayout rootLayout;

    @BindView(R.id.rv_tv_on_the_air)
    RecyclerView rvTvOnTheAir;

    @BindView(R.id.rv_movie_now_playing)
    RecyclerView rvMovieNowPlaying;

    @BindView(R.id.rv_movie_upcoming)
    RecyclerView rvMovieUpcoming;


    MainTvAdapter adapterTvOnTheAir;

    MainMovieAdapter adapterMovieNowPlaying;

    MainMovieAdapter adapterMovieUpcoming;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        mainPresenter.onViewReady();

        startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerMainComponent.builder()
                .appComponent(getApplicationComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpUiComponents() {

        adapterTvOnTheAir = new MainTvAdapter();
        rvTvOnTheAir.setLayoutManager(new LinearLayoutManager(this));
        rvTvOnTheAir.setMotionEventSplittingEnabled(false);
        rvTvOnTheAir.setHasFixedSize(true);
        rvTvOnTheAir.setNestedScrollingEnabled(false);
        rvTvOnTheAir.setAdapter(adapterTvOnTheAir);

        adapterMovieNowPlaying = new MainMovieAdapter();
        rvMovieNowPlaying.setLayoutManager(new LinearLayoutManager(this));
        rvMovieNowPlaying.setMotionEventSplittingEnabled(false);
        rvMovieNowPlaying.setHasFixedSize(true);
        rvMovieNowPlaying.setNestedScrollingEnabled(false);
        rvMovieNowPlaying.setAdapter(adapterMovieNowPlaying);

        adapterMovieUpcoming = new MainMovieAdapter();
        rvMovieUpcoming.setLayoutManager(new LinearLayoutManager(this));
        rvMovieUpcoming.setMotionEventSplittingEnabled(false);
        rvMovieUpcoming.setHasFixedSize(true);
        rvMovieUpcoming.setNestedScrollingEnabled(false);
        rvMovieUpcoming.setAdapter(adapterMovieUpcoming);

    }

    @Override
    public boolean isConnect() {
        return Utils.isConnected(this);
    }

    @Override
    public void showSnackBar(@StringRes int message) {
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void loadOnTheAirTvList(List<ResultTv> tvList) {
        adapterTvOnTheAir.setTvList(tvList);
    }

    @Override
    public void loadNowPlayingMovieList(List<ResultMovie> movieList) {
        adapterMovieNowPlaying.setMovieList(movieList);
    }

    @Override
    public void loadUpcomingMovieList(List<ResultMovie> movieList) {
        adapterMovieUpcoming.setMovieList(movieList);
    }
}
