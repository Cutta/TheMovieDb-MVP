package com.andcun.themoviedb_mvp.ui.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.andcun.themoviedb_mvp.R;
import com.andcun.themoviedb_mvp.data.rest.model.ResultTv;
import com.andcun.themoviedb_mvp.di.tv.DaggerTvComponent;
import com.andcun.themoviedb_mvp.di.tv.TvModule;
import com.andcun.themoviedb_mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by andani on 2.06.2017.
 */

public class TvActivity extends BaseActivity implements TvContract.View {

    public static final String EXTRA_TV_TYPE_ORDINAL = "tv_type_ordinal";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_tv)
    RecyclerView recyclerView;

    @Inject
    TvPresenter mPresenter;

    private TvType mTvType;
    private TvAdapter mAdapter;

    public static Intent newIntent(Context context, @NonNull TvType tvType) {
        Intent intent = new Intent(context, TvActivity.class);
        intent.putExtra(EXTRA_TV_TYPE_ORDINAL, tvType.ordinal());
        return intent;
    }

    //region Override Methods

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tv;
    }

    @Override
    protected void setUpUiComponents() {
        setupToolbar();
        setupRecyclerView();
    }

    @Override
    protected void resolveDaggerDependency() {
        getExtras();
        DaggerTvComponent.builder()
                .appComponent(getApplicationComponent())
                .tvModule(new TvModule(this, mTvType))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        mPresenter.onViewReady();
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

    //endregion

    //region Setup Methods

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey(EXTRA_TV_TYPE_ORDINAL))
            mTvType = TvType.TopRated;
        else
            mTvType = TvType.values()[extras.getInt(EXTRA_TV_TYPE_ORDINAL)];
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(mTvType.getTitle()));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setupRecyclerView() {
        mAdapter = new TvAdapter(new ArrayList<ResultTv>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + firstVisibleItemPosition) >= (totalItemCount - 2)
                        && firstVisibleItemPosition >= 0)
                    mPresenter.onScrollToEndOfList();
            }
        });
    }

    //endregion

    //region View Methods

    @Override
    public void loadMoreTv(List<ResultTv> tvList) {
        mAdapter.loadMoreTv(tvList);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //endregion

}