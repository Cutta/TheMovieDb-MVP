package com.andcun.themoviedb_mvp.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.andcun.themoviedb_mvp.TmdbApp;
import com.andcun.themoviedb_mvp.di.app.AppComponent;
import com.andcun.themoviedb_mvp.util.Utils;

import butterknife.ButterKnife;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final int REMAIN_ITEM_COUNT_TO_LOAD_MORE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState);
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        resolveDaggerDependency();
        setUpUiComponents();
    }

    protected AppComponent getApplicationComponent() {
        return ((TmdbApp) (getApplication())).getAppComponent();
    }

    protected void resolveDaggerDependency() {

    }

    public boolean isConnected() {
        return Utils.isConnected(BaseActivity.this);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void setUpUiComponents();

}