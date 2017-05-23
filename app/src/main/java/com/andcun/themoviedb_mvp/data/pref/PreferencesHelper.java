package com.andcun.themoviedb_mvp.data.pref;


import io.reactivex.Observable;

/**
 * Created by cuneytcarikci on 23/05/2017.
 */

public interface PreferencesHelper<T> {

    Observable<T> save(String key, T value);

    Observable<T> get(String key, Class<T> generic);

    Observable<Boolean> clear();
}
