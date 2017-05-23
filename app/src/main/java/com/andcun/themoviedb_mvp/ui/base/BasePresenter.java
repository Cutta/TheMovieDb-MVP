package com.andcun.themoviedb_mvp.ui.base;


import javax.inject.Inject;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V view;

    public V getView() {
        return view;
    }

}