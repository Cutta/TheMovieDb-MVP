package com.andcun.themoviedb_mvp.ui.discover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.di.discover.DaggerDiscoverComponent;
import com.andcun.themoviedb_mvp.di.discover.DiscoverModule;
import com.andcun.themoviedb_mvp.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by cuneytcarikci on 24/05/2017.
 */

public class DiscoverActivity extends BaseActivity implements DiscoverContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_discover)
    RecyclerView rvDiscover;

    @Inject
    DiscoverPresenter discoverPresenter;

    DiscoverAdapter<ResultMovie> discoverMovieAdapter;


    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        discoverPresenter.onViewReady();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerDiscoverComponent.builder()
                .appComponent(getApplicationComponent())
                .discoverModule(new DiscoverModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discover;
    }

    @Override
    protected void setUpUiComponents() {

        setRecyclerView();

        setToolBar();

    }

    private void setToolBar() {//// TODO: 26/05/2017 şimdilik böyle
        toolbar.setTitle("Keşfet");
    }

    private void setRecyclerView() {
        discoverMovieAdapter = new DiscoverAdapter<>();
        rvDiscover.setLayoutManager(new LinearLayoutManager(this));
        rvDiscover.setMotionEventSplittingEnabled(false);
        rvDiscover.setHasFixedSize(true);
        rvDiscover.setNestedScrollingEnabled(false);
        rvDiscover.setAdapter(discoverMovieAdapter);
    }

    @Override
    public void loadDiscoverMovie(List<ResultMovie> movieList) {
        discoverMovieAdapter.setDiscoverList(movieList);
    }

    @Override
    public void loadDiscoverTv(List<ResultTv> tvList) {
        // discoverMovieAdapter.setDiscoverList(tvList);
    }
}
