package com.andcun.themoviedb_mvp.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.di.movie.DaggerMovieComponent;
import com.andcun.themoviedb_mvp.di.movie.MovieModule;
import com.andcun.themoviedb_mvp.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by cuneytcarikci on 01/06/2017.
 */

public class MovieActivity extends BaseActivity implements MovieContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;

    @Inject
    MoviePresenter moviePresenter;

    MovieAdapter<ResultMovie> movieAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        moviePresenter.onViewReady();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerMovieComponent.builder()
                .appComponent(getApplicationComponent())
                .movieModule(new MovieModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    protected void setUpUiComponents() {

        setRecyclerView();

        setToolBar();

    }

    private void setToolBar() {//// TODO: 26/05/2017 şimdilik böyle
        toolbar.setTitle(R.string.toolbar_title_movies);
    }

    private void setRecyclerView() {

        movieAdapter = new MovieAdapter<>();
        layoutManager = new LinearLayoutManager(this);
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setMotionEventSplittingEnabled(false);
        rvMovie.setHasFixedSize(true);
        rvMovie.setAdapter(movieAdapter);

        rvMovie.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void loadMovieList(List<ResultMovie> movieList) {
        movieAdapter.setmovieList(movieList);
    }

    @Override
    public void loadMoreMovieList(List<ResultMovie> movieList) {
        movieAdapter.addmovieList(movieList);
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

            if ((visibleItemCount + firstVisibleItemPosition) >= (totalItemCount - REMAIN_ITEM_COUNT_TO_LOAD_MORE)
                    && firstVisibleItemPosition >= 0) {
                moviePresenter.decideLoadMore(totalItemCount);

            }
        }
    };
}
