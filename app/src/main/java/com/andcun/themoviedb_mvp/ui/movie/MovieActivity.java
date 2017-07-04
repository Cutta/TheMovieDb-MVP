package com.andcun.themoviedb_mvp.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultMovie;
import com.andcun.themoviedb_mvp.di.movie.DaggerMovieComponent;
import com.andcun.themoviedb_mvp.di.movie.MovieModule;
import com.andcun.themoviedb_mvp.ui.base.BaseActivity;
import com.andcun.themoviedb_mvp.util.Constant;

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

    MovieListType listType = MovieListType.Popular;

    public static Intent newIntent(Context context, MovieListType listType) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.EXTRA_LIST_TYPE, listType.ordinal());

        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        moviePresenter.onViewReady();
    }

    @Override
    protected void resolveDaggerDependency() {
        getExtras();
        DaggerMovieComponent.builder()
                .appComponent(getApplicationComponent())
                .movieModule(new MovieModule(this, listType))
                .build()
                .inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(Constant.EXTRA_LIST_TYPE)) {

            listType = MovieListType.values()[extras.getInt(Constant.EXTRA_LIST_TYPE)];

        }
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

    private void setToolBar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(listType.getTitle()));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
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
