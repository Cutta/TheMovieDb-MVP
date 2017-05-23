package com.andcun.themoviedb_mvp.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andcun.themoviedb_mvp.TmdbApp;
import com.andcun.themoviedb_mvp.di.app.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by cuneytcarikci on 02/05/2017.
 */


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewReady();
    }

    @CallSuper
    protected void onViewReady() {
        resolveDaggerDependency();
    }

    protected AppComponent getApplicationComponent() {
        return ((TmdbApp) ((Activity) getContext()).getApplication()).getAppComponent();
    }

    protected void resolveDaggerDependency() {

    }

    @LayoutRes
    protected abstract int getLayoutId();
}
