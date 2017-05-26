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
    LinearLayoutManager layoutManager;

    private boolean isLoading = false;

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
        layoutManager = new LinearLayoutManager(this);
        rvDiscover.setLayoutManager(layoutManager);
        rvDiscover.setMotionEventSplittingEnabled(false);
        rvDiscover.setHasFixedSize(true);
        rvDiscover.setNestedScrollingEnabled(false);
        rvDiscover.setAdapter(discoverMovieAdapter);

        rvDiscover.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void loadDiscoverMovie(List<ResultMovie> movieList) {
        discoverMovieAdapter.setDiscoverList(movieList);
    }

    @Override
    public void loadMoreDiscoverMovie(List<ResultMovie> movieList) {
        discoverMovieAdapter.addDiscoverList(movieList);
    }

    @Override
    public void loadDiscoverTv(List<ResultTv> tvList) {
        // discoverMovieAdapter.setDiscoverList(tvList);
    }

    @Override
    public void setLoadFlag(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= (totalItemCount - 2)//sonuna kadar gelmemizi beklemeden istek yapsın
                        && firstVisibleItemPosition >= 0) {
                    discoverPresenter.decideLoadMore(totalItemCount);//karar presenter da
                }
            }
        }
    };
}
